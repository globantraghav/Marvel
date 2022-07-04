package com.example.marvel.data.remote.dto.character_list

data class CharacterListDto(
    val attributionHTML: String,
    val attributionText: String,
    val code: String,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)
