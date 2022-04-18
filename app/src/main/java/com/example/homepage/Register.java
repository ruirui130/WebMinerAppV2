package com.example.homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {


    EditText name, num, Email, Pass;
    Button Reg;
    TextView lbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name1);
        num = findViewById(R.id.num);
        Email = findViewById(R.id.email1);
        Pass = findViewById(R.id.pass1);

        Reg = findViewById(R.id.reg);

        lbtn = findViewById(R.id.lbtn);
        Reg.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                String Name, Num, Mail, passw;

                Name = name.getText().toString();
                Num = num.getText().toString();
                Mail = Email.getText().toString();
                passw = Pass.getText().toString();

                if (Name.equals("")) ;
                {
                    Toast.makeText(Register.this, "Name is Blank", Toast.LENGTH_SHORT).show();
                }
                if (Num.equals("")) ;
                {
                    Toast.makeText(Register.this, "Num is Blank", Toast.LENGTH_SHORT).show();
                }
                if (Mail.equals("")) ;
                {
                    Toast.makeText(Register.this, "Email is Blank", Toast.LENGTH_SHORT).show();
                }
                if (passw.equals("")) ;
                {
                    Toast.makeText(Register.this, "Password is Blank", Toast.LENGTH_SHORT).show();
                }


            }


        });
        lbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent R= new Intent(Register.this, com.example.homepage.Login.class);
                startActivity(R);
                finish();

            }
        });
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent R= new Intent(Register.this, com.example.homepage.Login.class);
                startActivity(R);
                finish();

            }
        });
    }
}