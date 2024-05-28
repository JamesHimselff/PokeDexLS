package com.example.pokedexls.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pokedexls.MainScreen;
import com.example.pokedexls.Model.Pokemon;
import com.example.pokedexls.PokemonAdapter;
import com.example.pokedexls.R;
import java.util.List;

public class NewPokedexFragment extends Fragment {

    private RecyclerView recyclerView;
    private PokemonAdapter pokemonAdapter;
    private List<Pokemon> pokemons;

    public NewPokedexFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize your Pokémon list here or get it from the activity
        if (getActivity() instanceof MainScreen) {
            pokemons = ((MainScreen) getActivity()).pokemons;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pokedex_fragment, container, false);

        recyclerView = view.findViewById(R.id.pokemon_recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);
        pokemonAdapter = new PokemonAdapter(pokemons);
        recyclerView.setAdapter(pokemonAdapter);

        return view;
    }

    public void updatePokemonList(List<Pokemon> newPokemons) {
        this.pokemons = newPokemons;
        pokemonAdapter.notifyDataSetChanged();
    }
}
