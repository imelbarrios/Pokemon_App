package com.example.pokemon_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {


    private Button listBtn;
    private Button ultimateBtn;
    private Button logOut;
    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        listBtn = (Button) findViewById(R.id.list_button);
        ultimateBtn = (Button) findViewById(R.id.ultimate_list);
        logOut = (Button) findViewById(R.id.logout_button);

        listBtn.setOnClickListener(this);
        logOut.setOnClickListener(this);

    }


    public void signOut() {

        firebaseAuth.signOut();
        updateUI(null);
    }

    private void onList(){
        progressDialog.setMessage("Cargando...");
        progressDialog.show();
        Intent intent = new Intent(MenuActivity.this, ListActivity.class);
        startActivity(intent);

    }

    private void updateUI(FirebaseUser user) {
        if(user != null){

            /*Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);*/
        }else{
            progressDialog.setMessage("Cerrando Sesion");
            progressDialog.show();
            Intent intent = new Intent(MenuActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();

        if(i == R.id.list_button){
           onList();
        }

        if(i == R.id.logout_button){
            signOut();
        }

    }
}
