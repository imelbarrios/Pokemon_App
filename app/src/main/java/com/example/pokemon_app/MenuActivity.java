package com.example.pokemon_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button logoutBtn;
    private Button listBtn;
    private Button ultimateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        logoutBtn = (Button) findViewById(R.id.logout_button);
        listBtn = (Button) findViewById(R.id.list_button);
        ultimateBtn = (Button) findViewById(R.id.ultimate_list);

        logoutBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
