package com.example.pokedexls;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.pokedexls.Fragments.FragmentChangeListener;
import com.example.pokedexls.Fragments.NewPokedexFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainScreen extends AppCompatActivity implements FragmentChangeListener {

    private String email;
    private String username;
    BottomNavigationView navigationView;

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

        navigationView = findViewById(R.id.navBar);
        navigationView.setOnItemSelectedListener(listener);

        NewPokedexFragment fragment = new NewPokedexFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentMaster, fragment);
        fragmentTransaction.commit();

    }

    private final NavigationBarView.OnItemSelectedListener listener = menuItem -> {
        switch (menuItem.getItemId()) {

            case R.id.menu_pokedex:
                NewPokedexFragment pokedexFragment = new NewPokedexFragment();
                FragmentTransaction pokedexFragmentTransaction = getSupportFragmentManager().beginTransaction();
                pokedexFragmentTransaction.replace(R.id.fragmentMaster, pokedexFragment);
                pokedexFragmentTransaction.commit();
                return true;

            case R.id.menu_trainer:
                //anar cap al trainer
                System.out.println("Trainer menu");
                return true;

            case R.id.menu_shop:
                //anar cap al shop
                System.out.println("Shop menu");
                return true;
        }
        return false;
    };

    @Override
    public void setPokedexFragment() {

    }

    @Override
    public void setTrainerFragment() {

    }

    @Override
    public void shopFragment() {

    }
}