package com.example.pokemon_app.models;
import java.util.ArrayList;

public class PokemonResponse {

    private ArrayList<PokemonModel> results;

    public ArrayList<PokemonModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<PokemonModel> results) {
        this.results = results;
    }
}
