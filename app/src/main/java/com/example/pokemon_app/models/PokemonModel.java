package com.example.pokemon_app.models;

public class PokemonModel {
    private int id;
    private String name;
    private String url;
    private int height;
    private int weight;

    public PokemonModel(int id, String name, String url, int height, int weight) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.height = height;
        this.weight = weight;
    }

    public Integer getId() {
        String[] urlPartes = url.split("/");
        return Integer.parseInt(urlPartes[urlPartes.length - 1]);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
