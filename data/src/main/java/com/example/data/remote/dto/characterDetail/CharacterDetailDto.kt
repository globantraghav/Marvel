package com.example.data.remote.dto.characterDetail

data class CharacterDetailDto(
    val data: Data
)

data class Data(
    val results: List<Result>
)

data class Result(
    val description: String?,
    val id: Int?,
    val name: String?,
    val thumbnail: Thumbnail
)

data class Thumbnail(
    val extension: String?,
    val path: String?
)
