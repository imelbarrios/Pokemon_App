package com.example.pokemon_app;

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

import com.example.pokemon_app.models.PokemonModel;

import java.util.List;




public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private List<PokemonModel> items;
    private Context context;

    public static class ListViewHolder extends RecyclerView.ViewHolder{
        public CardView itemCard;
        public ImageView imagen;
        public TextView nombre;
        public TextView descrip;

        public ListViewHolder(View v){
            super(v);
            itemCard = (CardView) v.findViewById(R.id.item_row);
            imagen = (ImageView) v.findViewById(R.id.img_pokemon);
            nombre = (TextView) v.findViewById(R.id.name_pokemon);
            descrip = (TextView) v.findViewById(R.id.descri_pokemon);


        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public ListAdapter(List<PokemonModel> items){
        this.items = items;
    }

    public List<PokemonModel> getItems(){
        return this.items;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup,int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_row,viewGroup,false);
        return new ListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListViewHolder viewHolder, final int i) {

        viewHolder.nombre.setText(items.get(i).getName());
        viewHolder.imagen.setImageResource(items.get(i).getUrl());
        viewHolder.descrip.setText("Descripcion: " + items.get(i).getDesciption());

        viewHolder.itemCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("curImagen", items.get(i).getUrl());
                bundle.putString("curNombre", items.get(i).getName());
                bundle.putString("curDescripcion", items.get(i).getDesciption());
                Intent iconIntent = new Intent(view.getContext(), DescrpLisActivity.class);
                iconIntent.putExtras(bundle);
                view.getContext().startActivity(iconIntent);
            }
        });

    }



}