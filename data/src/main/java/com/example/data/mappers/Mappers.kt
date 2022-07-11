package com.example.data.mappers

import com.example.data.remote.dto.characterList.Result
import com.example.domain.model.ModelCharacter
import com.example.domain.model.ModelCharacterDetail

fun List<Result>.toDomain(): List<ModelCharacter> {

    return map {
        ModelCharacter(
            id = it.id ?: 0,
            name = it.name ?: "",
            description = it.description ?: "",
            thumbnail = it.thumbnail.path ?: "",
            thumbnailExt = it.thumbnail.extension ?: ""
        )
    }
}


fun com.example.data.remote.dto.characterDetail.Result.toDomain(): ModelCharacterDetail {

    return ModelCharacterDetail(
        id = id ?: 0,
        name = name ?: "",
        description = description ?: "",
        thumbnail = thumbnail.path ?: "",
        thumbnailExt = thumbnail.extension ?: ""
    )

}
