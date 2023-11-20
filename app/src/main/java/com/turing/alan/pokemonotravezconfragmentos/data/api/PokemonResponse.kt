package com.turing.alan.pokemonotravezconfragmentos.data.api

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    val results: List<PokemonListItemResponse>
)

data class PokemonListItemResponse(
    val name:String
)

data class PokemonDetailResponse(
    val id:Int,
    val name:String,
    val weight:Int,
    val height:Int,
    val sprites: PokemonSpritesResponse,
    //val bigImg: SpritesResponse
)

data class PokemonSpritesResponse(
    val front_default:String,
    val other:OtherImg
)

/*data class SpritesResponse(
    val otherImg: OtherImg
)*/

data class OtherImg(
    //Lo serializo porque la variable no puede contener "-"
    @SerializedName("official-artwork") val official_artwork: OfficialArtwork
)

data class OfficialArtwork(
    val front_default: String
)
