package com.example.domain.useCases

import com.example.common.Resource
import com.example.domain.model.ModelCharacter
import com.example.domain.repository.MarvelRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(
    private val repository: MarvelRepo
) {
    operator fun invoke(offset: Int): Flow<Resource<List<ModelCharacter>>> = flow {
        try {
            emit(Resource.Loading())
            val characterList =
                repository.getCharactersList(offset)
            emit(Resource.Success(characterList))
        } catch (e: HttpException) {
            emit(Resource.Error(Error + { e.localizedMessage }))
        } catch (io: IOException) {
            emit(Resource.Error(Error + { io.localizedMessage }))
        }
    }

    companion object {
        const val Error = "Error is"
    }
}