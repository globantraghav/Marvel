package com.example.marvel.domain.useCases

import com.example.marvel.common.Constants
import com.example.marvel.common.Resource
import com.example.marvel.domain.model.ModelCharacterDetail
import com.example.marvel.domain.repository.MarvelRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCharacterDetailsUseCase @Inject constructor(
    private val repository: MarvelRepo
) {
    operator fun invoke(charId: Int): Flow<Resource<ModelCharacterDetail>> = flow {
        try {
            emit(Resource.Loading())
            val modelCharacterDetails: ModelCharacterDetail =
                repository.getCharactersDetails(charId).data.results.first()
                    .toCharacterDetailsModel()
            emit(Resource.Success(modelCharacterDetails))
        } catch (e: HttpException) {
            emit(Resource.Error(Constants.Error + { e.localizedMessage }))
        } catch (io: IOException) {
            emit(Resource.Error(Constants.Error + { io.localizedMessage }))
        }
    }

}