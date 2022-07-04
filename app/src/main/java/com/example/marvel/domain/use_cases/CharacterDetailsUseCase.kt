package com.example.marvel.domain.use_cases

import com.example.marvel.common.Resource
import com.example.marvel.domain.model.CharacterDetail
import com.example.marvel.domain.repository.MarvelRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharacterDetailsUseCase @Inject constructor(
    private val repository: MarvelRepo
){
    operator fun invoke(charId:Int): Flow<Resource<CharacterDetail>> = flow {
        try {
            emit(Resource.Loading())
            val characterDetails:CharacterDetail = repository.getCharactersDetails(charId).data.results[0].toCharacterDetail()
            emit(Resource.Success(characterDetails))
        }

        catch (e: HttpException){
            emit(Resource.Error("Error is ${e.localizedMessage}"))
        }
        catch (io: IOException){
            emit(Resource.Error("Error is ${io.localizedMessage}"))
        }
    }

}