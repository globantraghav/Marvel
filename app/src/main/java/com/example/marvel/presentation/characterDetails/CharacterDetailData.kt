package com.example.marvel.presentation.characterDetails

import com.example.marvel.common.Constants
import com.example.marvel.domain.model.ModelCharacterDetail

data class CharacterDetailData(
    var isLoading: Boolean = false,
    var modelCharacterDetails: ModelCharacterDetail? = null,
    var error: String = Constants.Empty_String
)