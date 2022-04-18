package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

public class Second_activity extends AppCompatActivity {
    private Handler handler1;
    TextView priceview;
    TextView openview;
    TextView prevview;
    TextView volview;
    TextView avolview;
    TextView nameview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // initialize views
        priceview = findViewById(R.id.price);
        openview= findViewById(R.id.openVal);
        prevview=findViewById(R.id.prev);
        volview=findViewById(R.id.vol);
        avolview=findViewById(R.id.avol);
        nameview=findViewById(R.id.stockname);

        // intent object to bind code and launch tasks
        Intent intent = new Intent();

        // getStringExtra to get values
        //checking whether read and write input correctly //-*- for testing purposes-*-//
        String str0 = intent.getStringExtra("message_key");

        // Handler is needed for repeating tasks
        handler1 = new Handler();
        startRepeatingTask();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }

    // mToastRunnable is to let the program to run repeatedly
    Runnable mToastRunnable = new Runnable() {
        @Override
        public void run() {
            try{
                new webScrape().execute();
            } finally {
                // 1000 ms or 1 second
                int mInterval = 1000;
                // using postDelayed to achieve repeated run
                handler1.postDelayed(mToastRunnable, mInterval);
            }
        }
    };

    // start the repeatable task
    void startRepeatingTask(){
        mToastRunnable.run();
    }

    // stop the repeatable task
    void stopRepeatingTask(){
        handler1.removeCallbacks(mToastRunnable);
    }

    // extending AsyncTask to perform web-scraping
    @SuppressLint("StaticFieldLeak")
    public class webScrape extends AsyncTask<Void,Void,Void> {
        String price;

        String o;
        String p;
        String v;
        String av;
        String stockName;

        @Override
        protected Void doInBackground(Void... params) {
            try {

                Intent intent = getIntent();

                // get the user input
                String str0 = intent.getStringExtra("message_key");

                // url0 to be modified later
                URL url0 = new URL("https://fiance.yahoo.com/quote/");

                // third party library Jsoup was used to read website and record its data
                // url0+str0 would get the user to the corresponding fiance page
                // Since we found we can use .../quote/(input) to get to any page (if valid)
                Document document = Jsoup.connect(url0 + str0).get();

                // String ting and info are being css selected and are the information we need
                String ting = document.select("#quote-header-info").text();
                String info1 = document.select("#quote-summary").text();

                int marker = 0;

                // split the array by space
                String[] splitted = ting.split(" ");
                //System.out.println(splitted[17]); //-*-print only for testing purposes-*-//

                String[] splitinfo1 = info1.split(" ");

                // by using replaceAll to get rid of unnecessary elements
                for (int j = 0; j < splitinfo1.length; j++) {
                    if (splitinfo1[j].equals("Open")) {

                        String str1 = splitinfo1[j + 1].replaceAll("-", " ");
                        str1 = str1.replaceAll("\\+", " ");
                        str1 = str1.replaceAll(",", "");

                        String[] spl1 = str1.split(" ");

                        o = spl1[0];


                    }
                    // read and distinguish different stock status
                    if (splitinfo1[j].equals("Open")) {

                        String str1 = splitinfo1[j + 1].replaceAll("-", " ");
                        str1 = str1.replaceAll("\\+", " ");
                        str1 = str1.replaceAll(",", "");

                        String[] spl1 = str1.split(" ");

                        o = spl1[0];


                    }
                    if (splitinfo1[j].equals("Close")) {

                        String str1 = splitinfo1[j + 1].replaceAll("-", " ");
                        str1 = str1.replaceAll("\\+", " ");
                        str1 = str1.replaceAll(",", "");

                        String[] spl1 = str1.split(" ");

                        p = spl1[0];


                    }
                    if (splitinfo1[j].equals("Volume") && marker == 0) {

                        String str1 = splitinfo1[j + 1].replaceAll("-", " ");
                        str1 = str1.replaceAll("\\+", " ");
                        str1 = str1.replaceAll(",", "");

                        String[] spl1 = str1.split(" ");

                        v = spl1[0];
                        marker += 1;


                    }
                    if (splitinfo1[j].equals("Volume") && marker != 0) {

                        String str1 = splitinfo1[j + 1].replaceAll("-", " ");
                        str1 = str1.replaceAll("\\+", " ");
                        str1 = str1.replaceAll(",", "");

                        String[] spl1 = str1.split(" ");

                        av = spl1[0];


                    }
                    for (int i = 0; i < splitted.length; i++) {
                        if (i==0){
                            String str = splitted[i].replaceAll("-", " ");
                            str = str.replaceAll("\\+", " ");
                            str = str.replaceAll(",", "");

                            String[] splitted2 = str.split(" ");
                            stockName = splitted2[0];
                        }

                        if (splitted[i].equals("watchlist")) {
                            if (!splitted[i + 1].equals("Visitors")) {
                                String str = splitted[i + 1].replaceAll("-", " ");
                                str = str.replaceAll("\\+", " ");
                                str = str.replaceAll(",", "");

                                String[] splitted2 = str.split(" ");
                                price = splitted2[0];

                                // when the String price is not followed by an visitor
                                //-*-print only for testing purposes-*-//
                                //System.out.println("No visitors detected" + "\n" + price);
                            } else {
                                String str = splitted[i + 5].replaceAll("-", " ");
                                str = str.replaceAll("\\+", " ");
                                str = str.replaceAll(",", "");

                                String[] splitted2 = str.split(" ");
                                price = splitted2[0];

                                // when the String price is followed by an visitor
                                //-*-print only for testing purposes-*-//
                                //System.out.println("Visitors detected" + "\n" + price);
                            }
                        }
                    }
                }
            }catch (IOException e) {
                // catch and throw errors (does not work :D)
                e.printStackTrace();
            } return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            // set the text to its corresponding value
            priceview.setText(price);
            volview.setText(v);
            avolview.setText(av);
            prevview.setText(p);
            nameview.setText(stockName);
            openview.setText(o);
        }

    }

}