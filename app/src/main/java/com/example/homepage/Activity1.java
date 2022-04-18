package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity1 extends AppCompatActivity {

    // initialize the text and button
    EditText send_text;
    Button send_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        // let the buttons and texts find their id in the XML file
        send_button= findViewById(R.id.send_button_id);
        send_text = findViewById(R.id.send_text_id);

        // will only run with clicks of the button
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                // casting the user input value to String
                String str = send_text.getText().toString();

                // the intent object is to send data to Second_activity class
                Intent intent = new Intent(getApplicationContext(), Second_activity.class);

                // adding things into the intent object
                intent.putExtra("message_key", str);

                // execute the intent
                startActivity(intent);
            }
        });
    }
}