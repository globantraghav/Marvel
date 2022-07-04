package com.example.marvel.domain.repository

import com.example.marvel.data.remote.dto.character_detail.CharacterDetailDto
import com.example.marvel.data.remote.dto.character_list.CharacterListDto

interface MarvelRepo {
    suspend fun getCharacters(offset:Int) : CharacterListDto
    suspend fun getCharactersDetails(charId:Int) : CharacterDetailDto
}