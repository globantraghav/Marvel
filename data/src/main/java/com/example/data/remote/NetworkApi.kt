package com.example.data.remote

import com.example.common.Constant
import com.example.data.remote.dto.characterDetail.CharacterDetailDto
import com.example.data.remote.dto.characterList.CharacterListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApi {

    @GET("v1/public/characters")
    suspend fun getCharactersList(
        @Query("apikey") apikey: String = Constant.API_KEY,
        @Query("ts") ts: String = Constant.timestamp,
        @Query("hash") hash: String = Constant.hash(),
        @Query("offset") offset: String

    ): Response<CharacterListDto>

    @GET("v1/public/characters/{characterId}")
    suspend fun getCharactersDetails(
        @Path("characterId") characterId: Int,
        @Query("apikey") apikey: String = Constant.API_KEY,
        @Query("ts") ts: String = Constant.timestamp,
        @Query("hash") hash: String = Constant.hash()
    ): Response<CharacterDetailDto>

}