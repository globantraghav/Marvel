package com.example.marvel.data.remote.dto.character_detail

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)