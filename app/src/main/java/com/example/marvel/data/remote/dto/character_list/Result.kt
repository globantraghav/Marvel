package com.example.marvel.data.remote.dto.character_list

import com.example.marvel.domain.model.Character

data class Result(
    val description: String,
    val id: Int,
    val name: String,
    val thumbnail: Thumbnail,
){
    fun toCharacter():Character{
        return Character(
            id = id,
            name = name,
            description = description,
            thumbnail = thumbnail.path,
            thumbnailExt = thumbnail.extension
        )
    }

}

