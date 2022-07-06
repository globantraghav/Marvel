package com.example.marvel.data.remote.dto.characterList

import com.example.marvel.domain.model.ModelCharacter

data class Result(
    val description: String,
    val id: Int,
    val name: String,
    val thumbnail: Thumbnail,
) {
    fun toCharacterModel(): ModelCharacter {
        return ModelCharacter(
            id = id,
            name = name,
            description = description,
            thumbnail = thumbnail.path,
            thumbnailExt = thumbnail.extension
        )
    }

}

