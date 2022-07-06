package com.example.marvel.domain.useCases

import com.example.marvel.common.Constants
import com.example.marvel.common.Resource
import com.example.marvel.domain.model.ModelCharacter
import com.example.marvel.domain.repository.MarvelRepo
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
                repository.getCharactersList(offset).data.results.map { it.toCharacterModel() }
            emit(Resource.Success(characterList))
        } catch (e: HttpException) {
            emit(Resource.Error(Constants.Error + { e.localizedMessage }))
        } catch (io: IOException) {
            emit(Resource.Error(Constants.Error + { io.localizedMessage }))
        }
    }

}