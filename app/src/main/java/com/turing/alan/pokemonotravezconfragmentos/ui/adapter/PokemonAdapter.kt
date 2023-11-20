package com.turing.alan.pokemonotravezconfragmentos.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.turing.alan.pokemonotravezconfragmentos.data.model.Pokemon
import com.turing.alan.pokemonotravezconfragmentos.databinding.PokemonItemLayoutBinding

class PokemonAdapter():ListAdapter<Pokemon,PokemonAdapter.PokemonViewHolder>(DIFF_CALLBACK) {
    class PokemonViewHolder(private val binding: PokemonItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun pokemonMostrado(pokemon: Pokemon){
            binding.pokemonIdText.text = pokemon.id.toString()
            binding.pokemonNameText.text = pokemon.name
            binding.imgPokemon.load(pokemon.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonAdapter.PokemonViewHolder {
        val binding = PokemonItemLayoutBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        holder.pokemonMostrado(pokemon)
    }

    object DIFF_CALLBACK: DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(
            oldItem: Pokemon,
            newItem: Pokemon
        ) = oldItem.id == newItem.id


        override fun areContentsTheSame(
            oldItem: Pokemon,
            newItem: Pokemon
        ) = oldItem == newItem
    }
}