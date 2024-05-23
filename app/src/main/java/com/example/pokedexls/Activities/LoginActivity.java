package com.example.pokedexls.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokedexls.Fragments.FragmentChangeListener;
import com.example.pokedexls.MainScreen;
import com.example.pokedexls.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements FragmentChangeListener {
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

    @Override
    public void setPokedexFragment() {

    }

    @Override
    public void setTrainerFragment() {

    }

    @Override
    public void shopFragment() {

    }
    public void getAllPokemon(int offset) {
        String url = "https://pokeapi.co/api/v2/pokemon/?offset=" + offset + "&limit=" + 60;

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url, null,
                response -> {
                    try {
                        //Todo parsejar correctament la informació
                        // Parse response data (extract Pokemon names/URLs)
                        int count = response.getInt("count"); // Total number of Pokemon
                        JSONArray results = response.getJSONArray("results");
                        for (int i = 0; i < results.length(); i++) {
                            JSONObject pokemonInfo = results.getJSONObject(i);
                            String[] typesImage = new String[2];
                            String[] types = new String[2];
                            JSONArray typesObj = pokemonInfo.getJSONArray("types");
                            typesImage[0] = typesObj.getJSONObject(0).getString("name");
                            types[0] = typesObj.getJSONObject(0).getString("url");
                            typesImage[1] = (typesObj.getJSONObject(1) != null ? typesObj.getJSONObject(1).getString("url") : null);
                            types[1] = (typesObj.getJSONObject(1) != null ? typesObj.getJSONObject(1).getString("name") : null);

                            /*pokemons.add(new Pokemon(pokemonInfo.getString("name"),
                                    pokemonInfo.getJSONObject("sprites").getString("front_default"),
                                    pokemonInfo.getJSONObject("sprites").getString("back_default"),
                                    typesImage,
                                    types,
                                ));*/
                        }
                        // Check if more data needs to be fetched (offset < count)
                        if (offset + 60 < count) {
                            getAllPokemon(offset + 60); // Recursive call for next page
                        }
                    }catch (JSONException e){
                    }
                },
                error -> {
                        // Handle API call error
                }
            );
            Volley.newRequestQueue(this).add(request);
        }
    }
