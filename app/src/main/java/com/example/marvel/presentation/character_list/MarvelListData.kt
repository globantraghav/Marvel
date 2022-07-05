package com.example.marvel.presentation.character_list

import com.example.marvel.domain.model.Character

data class MarvelListData(
    var isLoading:Boolean=false,
    var characterList: List<Character> = emptyList(),
    var error:String = ""
)