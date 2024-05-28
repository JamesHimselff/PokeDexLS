package com.example.pokedexls;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pokedexls.Model.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private List<Pokemon> pokemonList;

    public PokemonAdapter(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pokedex_adapter, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);

        String capitalizedPokemonName = capitalizeFirstLetter(pokemon.getName());
        holder.pokemonName.setText(capitalizedPokemonName);
        Picasso.get().load(pokemon.getFrontImage()).into(holder.pokemonImage);
        Picasso.get().load(R.drawable.ic_pokedex).into(holder.pokemonPokeball);
    }

    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        TextView pokemonName;
        ImageView pokemonImage;
        ImageView pokemonPokeball;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonName = itemView.findViewById(R.id.pokemon_name);
            pokemonImage = itemView.findViewById(R.id.pokemon_image);
            pokemonPokeball = itemView.findViewById(R.id.pokemon_pokeball);
        }
    }
}
