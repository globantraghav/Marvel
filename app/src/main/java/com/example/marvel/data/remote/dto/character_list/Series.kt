package com.example.marvel.data.remote.dto.character_list

data class Series(
    val available: String,
    val collectionURI: String,
    val items: List<Item>,
    val returned: String
)