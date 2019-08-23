package com.example.pokemon_app.apiservice;

import com.example.pokemon_app.models.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface PokemonApiService {



    @GET("pokemon")
    Call<PokemonResponse>obtenerLista(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{id}")
    Call<PokemonResponse>obtenerPokemon(@Path("id") int id);

}
