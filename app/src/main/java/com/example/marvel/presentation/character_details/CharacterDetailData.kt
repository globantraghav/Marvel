package com.example.marvel.presentation.character_details

import com.example.marvel.domain.model.CharacterDetail

data class CharacterDetailData(
    var isLoading:Boolean=false,
    var characterDetails: CharacterDetail?=null,
    var error:String = ""
)