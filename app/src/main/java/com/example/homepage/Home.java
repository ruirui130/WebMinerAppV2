package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import org.jsoup.*;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Objects;

public class Home extends AppCompatActivity {
    private Handler mHandler;
    TextView textView;
    TextView openviewdj;
    TextView prevviewdj;
    TextView voldj;
    TextView avoldj;
    TextView prevsp;
    TextView opensp;
    TextView volsp;
    TextView avolsp;
    TextView psp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Market Today");
        textView = findViewById(R.id.textView);
        openviewdj = findViewById(R.id.openVal);
        prevviewdj= findViewById(R.id.prevdj);
        voldj= findViewById(R.id.voldj);
        avoldj= findViewById(R.id.avoldj);
        prevsp= findViewById(R.id.prevsp);
        opensp= findViewById(R.id.opensp);
        volsp= findViewById(R.id.volsp);
        avolsp= findViewById(R.id.avolsp);
        psp= findViewById(R.id.psp);
        mHandler = new Handler();
        startRepeatingTask();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try{
                new webScrape().execute();
            }finally {
                int mInterval = 1000;
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    void startRepeatingTask(){
        mStatusChecker.run();
    }

    void stopRepeatingTask(){
        mHandler.removeCallbacks(mStatusChecker);
    }

    @SuppressLint("SetTextI18n")
    void TestRepeat(){
        textView.setText("TEST " + Math.random());
    }

    @SuppressLint("StaticFieldLeak")
    public class webScrape extends AsyncTask<Void,Void,Void> {
        String pricedj;
        String opendj;
        String pcdj;
        String vdj;
        String avdj;
        String pricesp;
        String opensp1;
        String pcsp;
        String vsp;
        String avsp;
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document document = Jsoup.connect("https://finance.yahoo.com/quote/%5EDJI/").get();

                //CSS queries to get pertinent info
                String ting = document.select("#quote-header-info").text();
                String info1 = document.select("#quote-summary").text();
                String[] splitinfo1 = info1.split(" ");
                String[] splitted = ting.split(" ");
                //split string by spaces
                //System.out.println(splitted[17]);
                int marker = 0;
                //count used to differentiate between volume and avg volume
                for (int j = 0; j < splitinfo1.length; j++) {
                    if (splitinfo1[j].equals("Open")) {

                        String str1 = splitinfo1[j + 1].replaceAll("-", " ");
                        str1 = str1.replaceAll("\\+", " ");
                        str1 = str1.replaceAll(",", "");

                        String[] spl1 = str1.split(" ");

                        opendj = spl1[0];


                    }
                    if (splitinfo1[j].equals("Close")) {

                        String str1 = splitinfo1[j + 1].replaceAll("-", " ");
                        str1 = str1.replaceAll("\\+", " ");
                        str1 = str1.replaceAll(",", "");

                        String[] spl1 = str1.split(" ");

                        pcdj = spl1[0];


                    }
                    if (splitinfo1[j].equals("Volume") && marker == 0) {

                        String str1 = splitinfo1[j + 1].replaceAll("-", " ");
                        str1 = str1.replaceAll("\\+", " ");
                        str1 = str1.replaceAll(",", "");

                        String[] spl1 = str1.split(" ");

                        vdj = spl1[0];
                        marker += 1;


                    }
                    if (splitinfo1[j].equals("Volume") && marker != 0) {

                        String str1 = splitinfo1[j + 1].replaceAll("-", " ");
                        str1 = str1.replaceAll("\\+", " ");
                        str1 = str1.replaceAll(",", "");

                        String[] spl1 = str1.split(" ");

                        avdj = spl1[0];


                    }
                    for (int i = 0; i < splitted.length; i++) {
                        if (splitted[i].equals("watchlist")) {
                            if (!splitted[i + 1].equals("Visitors")) {
                                String str = splitted[i + 1].replaceAll("-", " ");
                                str = str.replaceAll("\\+", " ");
                                str = str.replaceAll(",", "");

                                String[] splitted2 = str.split(" ");

                                pricedj = splitted2[0];

                                System.out.println("No visitors detected" + "\n" + pricedj);
                            } else {
                                String str = splitted[i + 5].replaceAll("-", " ");
                                str = str.replaceAll("\\+", " ");
                                str = str.replaceAll(",", "");

                                String[] splitted2 = str.split(" ");

                                pricedj = splitted2[0];

                                System.out.println("Visitors detected" + "\n" + pricedj);
                            }
                        }
                    }
                }
                    Document document2 = Jsoup.connect("https://finance.yahoo.com/quote/%5EGSPC/").get();

                    //CSS queries to get pertinent info
                    String ting2 = document2.select("#quote-header-info").text();
                    String info12 = document2.select("#quote-summary").text();
                    String[] splitinfo12 = info12.split(" ");
                    String[] splitted12 = ting2.split(" ");
                    //split string by spaces
                    //System.out.println(splitted[17]);
                    int marker2 = 0;
                    //count used to differentiate between volume and avg volume
                    for (int q = 0; q < splitinfo12.length; q++) {
                        if (splitinfo12[q].equals("Open")) {

                            String str1 = splitinfo12[q + 1].replaceAll("-", " ");
                            str1 = str1.replaceAll("\\+", " ");
                            str1 = str1.replaceAll(",", "");

                            String[] spl1 = str1.split(" ");

                            opensp1 = spl1[0];


                        }
                        if (splitinfo12[q].equals("Close")) {

                            String str1 = splitinfo12[q + 1].replaceAll("-", " ");
                            str1 = str1.replaceAll("\\+", " ");
                            str1 = str1.replaceAll(",", "");

                            String[] spl1 = str1.split(" ");

                            pcsp = spl1[0];


                        }
                        if (splitinfo12[q].equals("Volume") && marker2 == 0) {

                            String str1 = splitinfo12[q + 1].replaceAll("-", " ");
                            str1 = str1.replaceAll("\\+", " ");
                            str1 = str1.replaceAll(",", "");

                            String[] spl1 = str1.split(" ");

                            vsp = spl1[0];
                            marker2 += 1;


                        }
                        if (splitinfo12[q].equals("Volume") && marker2 != 0) {

                            String str1 = splitinfo12[q + 1].replaceAll("-", " ");
                            str1 = str1.replaceAll("\\+", " ");
                            str1 = str1.replaceAll(",", "");

                            String[] spl1 = str1.split(" ");

                            avsp = spl1[0];


                        }
                        for (int s = 0; s < splitted12.length; s++) {
                            if (splitted12[s].equals("watchlist")) {
                                if (!splitted12[s + 1].equals("Visitors")) {
                                    String str = splitted12[s + 1].replaceAll("-", " ");
                                    str = str.replaceAll("\\+", " ");
                                    str = str.replaceAll(",", "");

                                    String[] splitted2 = str.split(" ");

                                    pricesp = splitted2[0];

                                    System.out.println("No visitors detected" + "\n" + pricesp);
                                } else {
                                    String str = splitted12[s + 5].replaceAll("-", " ");
                                    str = str.replaceAll("\\+", " ");
                                    str = str.replaceAll(",", "");

                                    String[] splitted2 = str.split(" ");

                                    pricesp = splitted2[0];

                                    System.out.println("Visitors detected" + "\n" + pricesp);
                                }
                            }
                        }


                }} catch (IOException e) {
                e.printStackTrace();
            } return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            textView.setText(pricedj);
            openviewdj.setText(opendj);
            prevviewdj.setText(pcdj);
            voldj.setText(vdj);
            avoldj.setText(avdj);
            psp.setText(pricesp);
            opensp.setText(opensp1);
            prevsp.setText(pcsp);
            volsp.setText(vsp);
            avolsp.setText(avsp);
        }
    }

}