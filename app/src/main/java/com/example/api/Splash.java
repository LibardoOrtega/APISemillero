package com.example.api;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
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

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Persona element = (Persona) getIntent().getSerializableExtra("Persona");
        tv1splash = findViewById(R.id.tv1splash);
        tv2splash = findViewById(R.id.tv2splash);
        iconImageView = findViewById(R.id.iconImageView);

        tv1splash.setText(element.getId());
        tv2splash.setText(element.getTitle());
        //iconImageView.setImageIcon(Icon );
    }

    public void regresar(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}