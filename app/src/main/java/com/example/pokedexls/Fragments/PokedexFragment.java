package com.example.pokedexls.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.pokedexls.Model.Pokemon;
import com.example.pokedexls.PokemonAdapter;
import com.example.pokedexls.R;
import com.google.android.gms.tflite.acceleration.Model;

import java.util.ArrayList;

public class PokedexFragment extends AppCompatActivity {

    private ArrayList<Pokemon> pokemons;
    private FragmentChangeListener listener;
    private Integer i;
    private Button nextButton;
    private Button prevButton;
    private TextView page;
    private PokemonAdapter adapter;
    private RequestQueue requestQueue;

    public PokedexFragment(ArrayList<Pokemon> pokemons, FragmentChangeListener listener, Activity activity) {
        this.pokemons = pokemons;
        this.listener = listener;
        requestQueue = Volley.newRequestQueue(activity);
        this.i = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokedex_fragment);
        RecyclerView recyclerView = findViewById(R.id.pokemon_recycler_view);
        recyclerView.setNestedScrollingEnabled(false);

        adapter = new PokemonAdapter(pokemons.subList(15*i, (15*i)+15),listener ); // Assuming yourData is the list of all items
        recyclerView.setAdapter(adapter);

        /*nextButton = (Button) findViewById(R.id.next);
        prevButton = (Button) findViewById(R.id.previous);
        page = (TextView) findViewById(R.id.page);*/

        nextButton.setOnClickListener(v -> {
            if (i < pokemons.size()/15) {
                i++;
                page.setText("Page: " + i);
                adapter = new PokemonAdapter(pokemons.subList(15*i, (15*i)+15), listener);
                recyclerView.setAdapter(adapter);
            }
        });

        prevButton.setOnClickListener(v -> {
            if (i > 0) {
                i--;
                page.setText("Page: " + i);
                adapter = new PokemonAdapter(pokemons.subList(15*i, (15*i)+15), listener);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}