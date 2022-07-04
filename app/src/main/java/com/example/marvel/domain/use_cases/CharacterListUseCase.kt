package com.example.marvel.domain.use_cases

import com.example.marvel.common.Resource
import com.example.marvel.domain.model.Character
import com.example.marvel.domain.repository.MarvelRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharacterListUseCase @Inject constructor(
  private val repository:MarvelRepo
) {
    operator fun invoke(offset:Int): Flow<Resource<List<Character>>> = flow {
        try {
            emit(Resource.Loading())
            val characterList = repository.getCharacters(offset).data.results.map { it.toCharacter() }
            emit(Resource.Success(characterList))
        }

        catch (e: HttpException){
            emit(Resource.Error("Error is ${e.localizedMessage}"))
        }
        catch (io:IOException){
            emit(Resource.Error("Error is ${io.localizedMessage}"))
        }
    }

}