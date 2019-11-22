package com.example.tp_rendu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button)findViewById(R.id.id_button1tp1);
        button2=(Button)findViewById(R.id.id_button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"bouton a reagie",Toast.LENGTH_LONG)
                //.show();
                Intent myIntent = new Intent(MainActivity.this,TP_number1.class);
                startActivity(myIntent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"bouton a reagie",Toast.LENGTH_LONG)
                //.show();
                Intent myIntent = new Intent(MainActivity.this,TP_number2.class);
                startActivity(myIntent);
            }
        });

    }

}
