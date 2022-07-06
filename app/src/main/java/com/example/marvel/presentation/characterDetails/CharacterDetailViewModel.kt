package com.example.marvel.presentation.characterDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.common.Constants
import com.example.marvel.common.Resource
import com.example.marvel.domain.model.ModelCharacterDetail
import com.example.marvel.domain.useCases.GetCharacterDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
) : ViewModel() {

    private val _characterDetails = MutableLiveData(CharacterDetailData())
    val characterDetails: LiveData<CharacterDetailData> = _characterDetails

    private val _characterImageUrl = MutableLiveData(String())
    val characterImageUrl: LiveData<String> = _characterImageUrl

    fun getCharacterDetails(id: Int) {
        getCharacterDetailsUseCase(id).onEach { result ->

            when (result) {
                is Resource.Success -> {
                    _characterDetails.value =
                        CharacterDetailData(modelCharacterDetails = result.data)
                    setUrl(result.data)
                }
                is Resource.Error -> {
                    _characterDetails.value =
                        CharacterDetailData(error = result.message ?: Constants.Unexpected_Error)
                }
                is Resource.Loading -> {
                    _characterDetails.value = CharacterDetailData(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

    private fun setUrl(modelCharacterDetail: ModelCharacterDetail?){
        val url = "${
            modelCharacterDetail?.thumbnail?.replace(
                Constants.HTTP,
                Constants.HTTPS
            )
        }${Constants.IMAGE_EXTENSION}${modelCharacterDetail?.thumbnailExt}"

        _characterImageUrl.value = url

    }
}