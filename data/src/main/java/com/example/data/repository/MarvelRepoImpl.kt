package com.example.data.repository

import com.example.data.mappers.toDomain
import com.example.data.remote.NetworkApi
import com.example.data.remote.dto.characterDetail.CharacterDetailDto
import com.example.data.remote.dto.characterList.CharacterListDto
import com.example.data.remote.utils.SafeApiRequest
import com.example.domain.model.ModelCharacter
import com.example.domain.model.ModelCharacterDetail
import com.example.domain.repository.MarvelRepo
import javax.inject.Inject

class MarvelRepoImpl @Inject constructor(
    private val networkApi: NetworkApi
) : MarvelRepo, SafeApiRequest() {

    override suspend fun getCharactersList(offset: Int): List<ModelCharacter> {
        val response = safeApiRequest { networkApi.getCharactersList(offset = offset.toString()) }
        return response.data.results.toDomain()
    }

    override suspend fun getCharactersDetails(charId: Int): ModelCharacterDetail {
        val response = safeApiRequest { networkApi.getCharactersDetails(characterId = charId) }
        return response.data.results.first().toDomain()
    }
}