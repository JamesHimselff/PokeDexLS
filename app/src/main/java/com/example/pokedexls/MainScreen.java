package com.example.pokedexls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainScreen extends AppCompatActivity {

    private String email;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();


        FirebaseUser currentUser = mAuth.getCurrentUser();


        if (currentUser != null) {
            email = currentUser.getEmail();
        }

        if (email != null) {
            int atIndex = email.indexOf("@");
            if (atIndex != -1) {
                username = email.substring(0, atIndex);
            }
        }

        System.out.println(username);




    }
}