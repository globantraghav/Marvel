package com.example.marvel.data.repository

import com.example.marvel.data.remote.NetworkApi
import com.example.marvel.data.remote.dto.character_detail.CharacterDetailDto
import com.example.marvel.data.remote.dto.character_list.CharacterListDto
import com.example.marvel.domain.repository.MarvelRepo
import javax.inject.Inject

class MarvelRepoImpl  @Inject constructor(
  private val networkApi: NetworkApi
) :MarvelRepo {
    override suspend fun getCharacters(offset:Int): CharacterListDto {
        return networkApi.getCharacters(offset = offset.toString())
    }

    override suspend fun getCharactersDetails(charId: Int): CharacterDetailDto {
        return networkApi.getCharactersDetails(characterId = charId)

    }
}