package com.example.marvel.data.remote

import com.example.marvel.common.Constants
import com.example.marvel.data.remote.dto.characterDetail.CharacterDetailDto
import com.example.marvel.data.remote.dto.characterList.CharacterListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApi {

    @GET("v1/public/characters")
    suspend fun getCharactersList(
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timestamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: String

    ): CharacterListDto

    @GET("v1/public/characters/{characterId}")
    suspend fun getCharactersDetails(
        @Path("characterId") characterId: Int,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timestamp,
        @Query("hash") hash: String = Constants.hash()
    ): CharacterDetailDto
}