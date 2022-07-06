package com.example.marvel.presentation.characterList

import com.example.marvel.common.Constants
import com.example.marvel.domain.model.ModelCharacter

data class MarvelListData(
    var isLoading: Boolean = false,
    var modelCharacterList: List<ModelCharacter> = emptyList(),
    var error: String = Constants.Empty_String
)