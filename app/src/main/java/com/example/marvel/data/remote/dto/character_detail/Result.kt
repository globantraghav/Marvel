package com.example.marvel.data.remote.dto.character_detail

import com.example.marvel.domain.model.CharacterDetail

data class Result(
    val description: String,
    val id: Int,
    val name: String,
    val thumbnail: Thumbnail
    ){

    fun toCharacterDetail(): CharacterDetail {
        return CharacterDetail(
            id = id,
            name = name,
            description = description,
            thumbnail = thumbnail.path,
            thumbnailExt = thumbnail.extension
        )
    }
}