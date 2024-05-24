package com.example.pokedexls;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokedexls.Fragments.NewPokedexFragment;
import com.example.pokedexls.Model.Pokemon;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainScreen extends AppCompatActivity {

    private String email;
    private String username;
    BottomNavigationView navigationView;
    public List<Pokemon> pokemons;
    RequestQueue requestQueue;
    NewPokedexFragment pokedexFragment;

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

        pokemons = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);

        navigationView = findViewById(R.id.navBar);
        navigationView.setOnItemSelectedListener(listener);

        pokedexFragment = new NewPokedexFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentMaster, pokedexFragment);
        fragmentTransaction.commit();

        fetchAllPokemon();
    }

    private final NavigationBarView.OnItemSelectedListener listener = menuItem -> {
        switch (menuItem.getItemId()) {
            case R.id.menu_pokedex:
                pokedexFragment = new NewPokedexFragment();
                FragmentTransaction pokedexFragmentTransaction = getSupportFragmentManager().beginTransaction();
                pokedexFragmentTransaction.replace(R.id.fragmentMaster, pokedexFragment);
                pokedexFragmentTransaction.commit();
                return true;
            case R.id.menu_trainer:
                System.out.println("Trainer menu");
                return true;
            case R.id.menu_shop:
                System.out.println("Shop menu");
                return true;
        }
        return false;
    };

    private void fetchAllPokemon() {
        String url = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=10000";
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray results = response.getJSONArray("results");
                        for (int i = 0; i < results.length(); i++) {
                            JSONObject pokemonInfo = results.getJSONObject(i);
                            String pokeUrl = pokemonInfo.getString("url");
                            fetchPokemonDetails(pokeUrl);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    error.printStackTrace();
                }
        );
        requestQueue.add(request);
    }

    private void fetchPokemonDetails(String url) {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url, null,
                response -> {
                    try {
                        String id = response.getString("id");
                        String name = response.getString("name");
                        String frontImage = response.getJSONObject("sprites").getString("front_default");
                        String backImage = response.getJSONObject("sprites").getString("back_default");

                        JSONArray typesArray = response.getJSONArray("types");
                        String[] types = new String[typesArray.length()];
                        for (int i = 0; i < typesArray.length(); i++) {
                            types[i] = typesArray.getJSONObject(i).getJSONObject("type").getString("name");
                        }

                        int hp = response.getJSONArray("stats").getJSONObject(0).getInt("base_stat");

                        Pokemon pokemon = new Pokemon(id, name, frontImage, backImage, types, hp);
                        pokemons.add(pokemon);

                        // Sort pokemons by ID
                        Collections.sort(pokemons, new Comparator<Pokemon>() {
                            @Override
                            public int compare(Pokemon p1, Pokemon p2) {
                                return Integer.compare(Integer.parseInt(p1.getId()), Integer.parseInt(p2.getId()));
                            }
                        });

                        if (pokedexFragment != null) {
                            pokedexFragment.updatePokemonList(pokemons);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    error.printStackTrace();
                }
        );
        requestQueue.add(request);
    }
}
