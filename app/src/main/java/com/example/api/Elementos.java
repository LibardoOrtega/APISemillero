package com.example.api;

import static com.example.api.MainActivity.bitMapImage;
import static com.example.api.utils.Utils.convertBitmapToImagenView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class Elementos extends AppCompatActivity {

    private TextView tv1elementos;
    private TextView tv2elementos;
    private ImageView imageDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elementos);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Persona element = (Persona) getIntent().getSerializableExtra("Persona");
        tv1elementos = findViewById(R.id.tv1elementos);
        tv2elementos = findViewById(R.id.tv2elementos);
        imageDescription = findViewById(R.id.imageDescription);

        tv1elementos.setText(element.getId());
        tv2elementos.setText(element.getTitle());
        imageDescription.setImageDrawable(convertBitmapToImagenView(bitMapImage, this));
    }

    public void regresar(View view){
        onBackPressed();
    }
}