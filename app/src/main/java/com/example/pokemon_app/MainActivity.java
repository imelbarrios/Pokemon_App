package com.example.pokemon_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText TextEmail;
    private EditText TextPassword;
    private Button registerBtn;
    private Button loginBtn;
    private Button fbBtn;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        TextEmail = (EditText) findViewById(R.id.text_email);
        TextPassword = (EditText) findViewById(R.id.text_password);
        registerBtn = (Button) findViewById(R.id.register_button);
        loginBtn = (Button) findViewById(R.id.btn_login);
        fbBtn = (Button) findViewById(R.id.init_fb);

        progressDialog = new ProgressDialog(this);
        registerBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        fbBtn.setOnClickListener(this);

    }
        private void registerUser() {
            String email = TextEmail.getText().toString().trim();
            String password = TextPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Ingrese un email", Toast.LENGTH_LONG).show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Debe ingresar un password", Toast.LENGTH_LONG).show();
                return;
            }
            progressDialog.setMessage("Registrado");
            progressDialog.show();


            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this,"Se ha registrado email",Toast.LENGTH_LONG).show();
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                Toast.makeText( MainActivity.this, "Ya se encuentra registrado",Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }

                        }
                    });
        }

        private  void signIn(String email, String password){

            progressDialog.setMessage("Iniciando Sesion");
            progressDialog.show();

           firebaseAuth.signInWithEmailAndPassword(email,password)
                   .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               Toast.makeText(MainActivity.this,"Sesion iniciada",Toast.LENGTH_LONG).show();
                               FirebaseUser user = firebaseAuth.getCurrentUser();
                               updateUI(user);
                           } else {
                               Toast.makeText( MainActivity.this, "Error en petición",Toast.LENGTH_SHORT).show();
                               updateUI(null);
                           }
                       }
                   });
        }




    public void signOut() {
        firebaseAuth.signOut();

        updateUI(null);
    }

    private void updateUI(FirebaseUser user) {
        if(user != null){

            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if(i == R.id.register_button){
            registerUser();
        }

        if(i == R.id.btn_login){
            String email = TextEmail.getText().toString().trim();
            String password = TextPassword.getText().toString().trim();
            signIn(email, password);
        }

        if(i == R.id.init_fb){
            Intent intent = new Intent(MainActivity.this, FacebookLoginAct.class);
            startActivity(intent);
            finish();
        }

    }
}
