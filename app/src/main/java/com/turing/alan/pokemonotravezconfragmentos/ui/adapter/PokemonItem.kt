package com.turing.alan.pokemonotravezconfragmentos.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.turing.alan.pokemonotravezconfragmentos.data.api.PokemonListItemResponse

class PokemonItem(private val pokemonList:PokemonListItemResponse):ListAdapter<PokemonListItemResponse, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    object DIFF_CALLBACK: DiffUtil.ItemCallback<PokemonListItemResponse>() {
        override fun areItemsTheSame(
            oldItem: PokemonListItemResponse,
            newItem: PokemonListItemResponse
        ) = oldItem == newItem


        override fun areContentsTheSame(
            oldItem: PokemonListItemResponse,
            newItem: PokemonListItemResponse
        ) = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}