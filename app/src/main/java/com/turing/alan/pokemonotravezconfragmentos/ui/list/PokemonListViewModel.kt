package com.turing.alan.pokemonotravezconfragmentos.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.turing.alan.pokemonotravezconfragmentos.data.api.PokemonApiModel
import com.turing.alan.pokemonotravezconfragmentos.data.api.PokemonListApiModel
import com.turing.alan.pokemonotravezconfragmentos.data.api.PokemonRepository
import com.turing.alan.pokemonotravezconfragmentos.data.model.Pokemon
import kotlinx.coroutines.launch

class PokemonListViewModel(): ViewModel() {
    private val repository = PokemonRepository.getInstance()
    private val _pokemonUi = MutableLiveData<List<Pokemon>>()
    val pokemonUi: LiveData<List<Pokemon>>
        get() = _pokemonUi
    private val observer = Observer<PokemonListApiModel> { cadaPokemon ->
        _pokemonUi.value = cadaPokemon.pokemonList.map {
                elPokemonDeLaLista -> Pokemon(elPokemonDeLaLista.id, elPokemonDeLaLista.name)
        }
    }

    init {
        fetch()
    }

    fun fetch() {
        repository.pokemon.observeForever(observer)
        viewModelScope.launch {
            repository.fetch()
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository
            .pokemon
            .removeObserver(observer)
    }
}