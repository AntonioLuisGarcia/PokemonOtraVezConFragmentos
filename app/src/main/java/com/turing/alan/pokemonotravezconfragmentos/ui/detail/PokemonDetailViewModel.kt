package com.turing.alan.pokemonotravezconfragmentos.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turing.alan.pokemonotravezconfragmentos.data.api.PokemonRepository
import com.turing.alan.pokemonotravezconfragmentos.data.model.Pokemon
import kotlinx.coroutines.launch


class PokemonDetailViewModel(): ViewModel() {
    private val repository = PokemonRepository.getInstance()
    private val _pokemonUi = MutableLiveData<Pokemon>()
    val pokemonUi: LiveData<Pokemon>
        get() = _pokemonUi

    public fun fetch(name: String) {
        viewModelScope.launch {
            val pokemonApi = repository.fetchByName(name)
            _pokemonUi.value = Pokemon(pokemonApi.id,pokemonApi.name,pokemonApi.weight, pokemonApi.height, pokemonApi.front, pokemonApi.bigFront)
        }
    }
}