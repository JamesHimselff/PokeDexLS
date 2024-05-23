package com.example.pokedexls;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    private static List<Pokemon> data;
    private FragmentChangeListener listener;

    public PokemonAdapter(List<Pokemon> data, FragmentChangeListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.pokedex_adapter, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pokemon item = data.get(position);
        Picasso.get().load(item.getFrontImage()).into(holder.pokemonImage);
        holder.pokemonName.setText(item.getName());
        Picasso.get().load(item.getPokeballImage()).into(holder.pokemonPokeball);
    }



    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private FragmentChangeListener listener;
        TextView pokemonName;
        ImageView pokemonPokeball;
        ImageView pokemonImage;

        public ViewHolder(View itemView, FragmentChangeListener listener) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.listener = listener;
            pokemonName = itemView.findViewById(R.id.pokemon_name);
            pokemonImage = itemView.findViewById(R.id.pokemon_image);
            pokemonPokeball = itemView.findViewById(R.id.pokemon_pokeball);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();  // Get item position
            Pokemon pokemon = data.get(position);
            if (listener != null) {
                //todo change to pokemon fragment
            }
        }
    }
}