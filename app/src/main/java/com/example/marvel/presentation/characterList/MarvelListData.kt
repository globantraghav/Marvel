package com.example.marvel.presentation.characterList

import com.example.common.Constant
import com.example.domain.model.ModelCharacter

data class MarvelListData(
    var isLoading: Boolean = false,
    var modelCharacterList: List<ModelCharacter> = emptyList(),
    var error: String = Constant.Empty_String
)