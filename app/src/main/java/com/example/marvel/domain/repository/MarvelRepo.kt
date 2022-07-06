package com.example.marvel.domain.repository

import com.example.marvel.data.remote.dto.characterDetail.CharacterDetailDto
import com.example.marvel.data.remote.dto.characterList.CharacterListDto

interface MarvelRepo {
    suspend fun getCharactersList(offset: Int): CharacterListDto
    suspend fun getCharactersDetails(charId: Int): CharacterDetailDto
}