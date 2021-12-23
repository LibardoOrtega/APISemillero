package com.example.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.persona.PersonaapiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
     private TextView mJsonTxtView;
     List<Persona> elements;
     private RecyclerView recyclerView;
     private ListaPersonaAdapter listAdapter;
     private Retrofit retrofit;
     private SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        recyclerView = findViewById(R.id.listrecyclerview);
        search = findViewById(R.id.search);

        //mJsonTxtView = findViewById(R.id.jsonText);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        search.setOnQueryTextListener(this);
        getPersona(this);
    }

    private void getPersona(Context context) {
        //System.out.printf("DENTRO DE PERSONA");
        PersonaapiService personaapiService = retrofit.create(PersonaapiService.class);
        Call<List<Persona>> listCall = personaapiService.getPersona();
        listCall.enqueue(new Callback<List<Persona>>() {
            @Override
            public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {
                if(response.isSuccessful()){
                    List<Persona> listPer = response.body();
                    //System.out.printf("PERSONA :::::::::::::::::::::::::::"+listPer.get(0).getBody());
                    showData(listPer, context);
                }
            }

            @Override
            public void onFailure(Call<List<Persona>> call, Throwable t) {

            }
        });

    }
    private void showData(List<Persona> listPersona, Context context){
        ListaPersonaAdapter listAdapter = new ListaPersonaAdapter(listPersona,context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        listAdapter.filter(newText);
        return false;
    }
}