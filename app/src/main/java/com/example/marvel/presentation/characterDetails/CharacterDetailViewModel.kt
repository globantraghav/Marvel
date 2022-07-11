package com.example.marvel.presentation.characterDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Constant.Companion.Empty_String
import com.example.common.Resource
import com.example.domain.useCases.GetCharacterDetailsUseCase
import com.example.marvel.presentation.characterList.CharacterListViewModel.Companion.Unexpected_Error
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

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun getCharacterDetails(id: Int) {
        getCharacterDetailsUseCase(id).onEach { result ->

            when (result) {
                is Resource.Success -> {
                    _errorMessage.value = Empty_String
                    _characterDetails.value =
                        CharacterDetailData(modelCharacterDetails = result.data)
                }
                is Resource.Error -> {
                    _errorMessage.value = result.message ?: Unexpected_Error
                }
                is Resource.Loading -> {
                    _errorMessage.value = Empty_String
                }
            }
        }.launchIn(viewModelScope)
    }

    companion object {
        const val HTTP = "http"
        const val HTTPS = "https"
    }
}