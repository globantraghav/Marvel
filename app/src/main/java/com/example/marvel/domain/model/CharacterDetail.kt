package com.example.marvel.domain.model

class CharacterDetail(
    val id:Int,
    val name:String,
    val description:String,
    val thumbnail:String,
    val thumbnailExt:String,
    val comics:List<String>
)