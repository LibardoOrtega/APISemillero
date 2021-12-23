package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    TextView tv1splash;
    TextView tv2splash;
    ImageView iconImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Persona element = (Persona) getIntent().getSerializableExtra("Persona");
        tv1splash = findViewById(R.id.tv1splash);
        tv2splash = findViewById(R.id.tv2splash);
        iconImageView = findViewById(R.id.iconImageView);

        tv1splash.setText(element.getBody());
        tv2splash.setText(element.getTitle());
        //iconImageView.setImageIcon(Icon icon));
    }
}