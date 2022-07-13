package com.example.data.remote

import com.example.data.remote.dto.characterDetail.CharacterDetailDto
import com.example.data.remote.dto.characterList.CharacterListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

interface NetworkApi {

    @GET("v1/public/characters")
    suspend fun getCharactersList(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") ts: String = timestamp,
        @Query("hash") hash: String = hash(),
        @Query("offset") offset: String

    ): Response<CharacterListDto>

    @GET("v1/public/characters/{characterId}")
    suspend fun getCharactersDetails(
        @Path("characterId") characterId: Int,
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") ts: String = timestamp,
        @Query("hash") hash: String = hash()
    ): Response<CharacterDetailDto>

    companion object {
        const val BASE_URL = "https://gateway.marvel.com/"
        val timestamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = ""
        const val PRIVATE_KEY = ""

        fun hash(): String {
            val input = "${timestamp}${PRIVATE_KEY}${API_KEY}"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}

