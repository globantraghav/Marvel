package com.example.domain.useCases

import com.example.common.Constant
import com.example.common.Resource
import com.example.domain.model.ModelCharacterDetail
import com.example.domain.repository.MarvelRepo
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
                repository.getCharactersDetails(charId)
            emit(Resource.Success(modelCharacterDetails))
        } catch (e: HttpException) {
            emit(Resource.Error(Constant.Error + { e.localizedMessage }))
        } catch (io: IOException) {
            emit(Resource.Error(Constant.Error + { io.localizedMessage }))
        }
    }

}