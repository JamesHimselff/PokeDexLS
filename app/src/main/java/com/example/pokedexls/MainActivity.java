package com.example.pokedexls;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button signbutton;
    private Button registerbutton;

    private EditText editTextEmail;
    private EditText editTextPassword;

    String email, password;

    /******************************
     Se puede usar para login:

     hello@gmail.com
     hello123
     *****************************/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);



        signbutton = (Button) findViewById(R.id.sign_button);
        registerbutton = (Button) findViewById(R.id.register_button);
        editTextEmail = (EditText) findViewById(R.id.EditEmail);
        editTextPassword = (EditText) findViewById(R.id.EditPassword);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            // If I am already logged in
            showToast("Welcome back");
            /*************
             * Referencia a otra actividad
             *
            //startActivity(new Intent(this, MainActivity.class));

            **************/
            finish();
            return;
        }

        signbutton.setOnClickListener(v -> {
            email = editTextEmail.getText().toString();
            password = editTextPassword.getText().toString();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            //error
                        }
                    });
        });

       registerbutton.setOnClickListener(v -> {
            Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);
            MainActivity.this.startActivity(registerIntent);

        });

    }

 private void updateUI(FirebaseUser user) {

        /******Aqui habra referencia a otra actividad

       Intent map_intent = new Intent(EmailPasswordActivity.this, MainActivity.class);

       EmailPasswordActivity.this.startActivity(map_intent);
        *************/
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }


}