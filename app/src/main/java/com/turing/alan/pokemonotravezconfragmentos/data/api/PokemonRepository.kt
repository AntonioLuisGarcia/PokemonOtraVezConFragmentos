package com.turing.alan.pokemonotravezconfragmentos.data.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface  PokemonApi {
    @GET("api/v2/pokemon/{name}/")
    suspend fun fetchPokemon(@Path("name") name:String):PokemonDetailResponse

    @GET("api/v2/pokemon")
    suspend fun fetchPokemonList():PokemonListResponse //Luego se podran añadir los parametros
}


class PokemonRepository private constructor(private val api:PokemonApi) {

    private val _pokemon = MutableLiveData<PokemonListApiModel>()
    val pokemon: LiveData<PokemonListApiModel>
        get() = _pokemon

    companion object {
        private var _INSTANCE: PokemonRepository? = null
        fun getInstance(): PokemonRepository {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val pokemonApi = retrofit.create(PokemonApi::class.java)
            _INSTANCE = _INSTANCE ?: PokemonRepository(pokemonApi)
            return _INSTANCE!!

        }
    }

    suspend fun fetch() {

        val pokemonListResponse = api.fetchPokemonList()

        val listaDePokemonsConLosDetalles = mutableListOf<PokemonApiModel>()
        pokemonListResponse.results.forEach{p ->
            val detalleDelPokemon = api.fetchPokemon(p.name)
            val listadoDePokemon = PokemonApiModel(
                id = detalleDelPokemon.id,
                name = detalleDelPokemon.name,
                weight = detalleDelPokemon.weight,
                height = detalleDelPokemon.height,
                front = detalleDelPokemon.sprites.front_default,
                bigFront = detalleDelPokemon.sprites.other.official_artwork.front_default
            )
            listaDePokemonsConLosDetalles.add(listadoDePokemon)
        }

        val pokemonsParaElBehaviourSubject = PokemonListApiModel(listaDePokemonsConLosDetalles)
        _pokemon.postValue(pokemonsParaElBehaviourSubject)

        /*var pokemonDetailList = pokemonListResponse.results.map{
            val detailResponse = api.fetchPokemon(it.name)
            PokemonApiModel(detailResponse.id,
                detailResponse.name,
                detailResponse.weight,
                detailResponse.height,
                detailResponse.front)
        }

        val pokemonListApiModel = PokemonListApiModel(pokemonDetailList)
        _pokemon.value = pokemonListApiModel //tmb puede ser con pokemonDetailList solo
        */
    }

    suspend fun fetchByName(name: String): PokemonApiModel {
        val detailResponse = api.fetchPokemon(name)
        return PokemonApiModel(
            detailResponse.id,
            detailResponse.name,
            detailResponse.weight,
            detailResponse.height,
            detailResponse.sprites.front_default,
            detailResponse.sprites.other.official_artwork.front_default
        )
    }

}