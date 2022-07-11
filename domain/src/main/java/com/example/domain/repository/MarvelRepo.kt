package com.example.domain.repository

import com.example.domain.model.ModelCharacter
import com.example.domain.model.ModelCharacterDetail

interface MarvelRepo {
    suspend fun getCharactersList(offset: Int): List<ModelCharacter>
    suspend fun getCharactersDetails(charId: Int): ModelCharacterDetail
}