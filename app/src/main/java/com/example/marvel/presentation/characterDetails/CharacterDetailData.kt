package com.example.marvel.presentation.characterDetails

import com.example.common.Constant
import com.example.domain.model.ModelCharacterDetail

data class CharacterDetailData(
    var isLoading: Boolean = false,
    var modelCharacterDetails: ModelCharacterDetail? = null,
    var error: String = Constant.Empty_String
)