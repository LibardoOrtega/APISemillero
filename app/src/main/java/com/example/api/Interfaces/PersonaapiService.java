package com.example.api.Interfaces;

import com.example.api.Interactors.Persona;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PersonaapiService {

    @GET("posts")
    Call<List<Persona>> getPersona();

}
