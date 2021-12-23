package com.example.api.persona;

import com.example.api.Persona;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PersonaapiService {

    @GET("posts")
    Call<List<Persona>> getPersona();

}
