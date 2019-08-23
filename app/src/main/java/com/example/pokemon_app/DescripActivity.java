package com.example.pokemon_app;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class DescripActivity extends AppCompatActivity {
    TextView nombre;
    ImageView imagen;

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descrip);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      nombre = (TextView) findViewById(R.id.name_pokemon);
      imagen =(ImageView) findViewById(R.id.img_pokemon);


        nombre.setText(getIntent().getExtras().getString("curNombre"));
        Picasso.with(imagen.getContext())
                .load(getIntent().getExtras().getString("curImagen")).into(imagen);
    }

}
