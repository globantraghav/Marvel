package com.example.marvel.data.remote.dto.characterDetail

import com.example.marvel.domain.model.ModelCharacterDetail

data class Result(
    val description: String,
    val id: Int,
    val name: String,
    val thumbnail: Thumbnail
) {

    fun toCharacterDetailsModel(): ModelCharacterDetail {
        return ModelCharacterDetail(
            id = id,
            name = name,
            description = description,
            thumbnail = thumbnail.path,
            thumbnailExt = thumbnail.extension
        )
    }
}