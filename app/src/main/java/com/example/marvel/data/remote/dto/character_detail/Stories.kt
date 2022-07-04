package com.example.marvel.data.remote.dto.character_detail

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXXX>,
    val returned: Int
)