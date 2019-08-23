package com.example.pokemon_app;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemon_app.apiservice.PokemonApiService;
import com.example.pokemon_app.models.PokemonModel;
import com.example.pokemon_app.models.PokemonResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity {
    private static final String TAG = "POKE";

    private Retrofit retrofit;

    private int offset;
    private boolean recarga;


    private RecyclerView recyclerView;
    private ListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        recyclerView = (RecyclerView) findViewById(R.id.listado);
        adapter = new ListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
      final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();

                    if (recarga) {
                        if ((visibleItemCount) >= totalItemCount) {
                            Log.i(TAG, " Llegamos al final.");

                            recarga = false;
                            offset += 20;
                            obtenerDatos(offset);
                        }
                    }
                }
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recarga = true;
        offset = 0;
        obtenerDatos(offset);

    }

    private void obtenerDatos(int offset) {
        PokemonApiService service = retrofit.create(PokemonApiService.class);
        Call<PokemonResponse> pokemonResponseCall = service.obtenerLista(20, offset);
        pokemonResponseCall.enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                recarga = true;
                if (response.isSuccessful()) {
                    PokemonResponse pokemonResponse = response.body();
                    ArrayList<PokemonModel> listaPokemon  = pokemonResponse.getResults();
                    adapter.adiccionarListado(listaPokemon);


                }else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {

            }
        });


    }



}