package com.example.pokemon_app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokemon_app.models.PokemonModel;

import java.util.ArrayList;




public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

   private ArrayList<PokemonModel> items;
    private Context context;
    String img;

    private ProgressDialog progressDialog;

    public ListAdapter(Context context){
        this.context = context;
        items = new ArrayList<>();
    }



    @Override
    public int getItemCount() {
        return items.size();
    }


    public void adiccionarListado(ArrayList<PokemonModel> listaPokemon){
        items.addAll(listaPokemon);
        notifyDataSetChanged();
    }




    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup,int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_row,viewGroup,false);
        return new ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListViewHolder viewHolder,final int i) {
        viewHolder.nombre.setText(items.get(i).getName());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/" + items.get(i).getId() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.imagen);


        viewHolder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                img = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/" + items.get(i).getId() + ".png";
                bundle.putString("curImagen", img);
                bundle.putString("curNombre", items.get(i).getName());
                Intent iconIntent = new Intent(view.getContext(), DescripActivity.class);
                iconIntent.putExtras(bundle);
                view.getContext().startActivity(iconIntent);
            }
        });

    }

    public  class ListViewHolder extends RecyclerView.ViewHolder{

        public CardView itemCard;
        public ImageView imagen;
        public TextView nombre;


        public ListViewHolder(View v){
            super(v);
            itemCard = (CardView) v.findViewById(R.id.item_row);
            imagen = (ImageView) v.findViewById(R.id.img_pokemon);
            nombre = (TextView) v.findViewById(R.id.name_pokemon);



        }
    }



}