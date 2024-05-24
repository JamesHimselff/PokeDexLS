package com.example.pokedexls.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokedexls.MainScreen;
import com.example.pokedexls.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private Button signbutton;
    private Button registerbutton;

    private EditText editTextEmail;
    private EditText editTextPassword;

    private TextView header;

    String email, password;

    /******************************
     Se puede usar para login:

     hello@gmail.com
     hello123
     *****************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(this);

        signbutton = (Button) findViewById(R.id.sign_button);
        registerbutton = (Button) findViewById(R.id.register_button);
        editTextEmail = (EditText) findViewById(R.id.EditEmail);
        editTextPassword = (EditText) findViewById(R.id.EditPassword);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            showToast("Welcome back");
            Intent main_intent = new Intent(LoginActivity.this, MainScreen.class);
            main_intent.putExtra("key", currentUser);
            LoginActivity.this.startActivity(main_intent);

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
                            showToast(task.getResult().toString());
                        }
                    });
        });

       registerbutton.setOnClickListener(v -> {
            Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            LoginActivity.this.startActivity(registerIntent);
        });

    }

    private void updateUI(FirebaseUser user) {
        Intent main_intent = new Intent(LoginActivity.this, MainScreen.class);
        LoginActivity.this.startActivity(main_intent);
    }

    private void showToast(String message) {
        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
    }
}