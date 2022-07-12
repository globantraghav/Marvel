package com.example.marvel.presentation.characterList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Constant.Companion.Empty_String
import com.example.common.Resource
import com.example.domain.useCases.GetCharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterListUseCase: GetCharacterListUseCase
) : ViewModel() {

    private val _marvelList = MutableLiveData(MarvelListData())
    val marvelList: LiveData<MarvelListData>
         get() = _marvelList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun getCharactersList(offset: Int) {
        characterListUseCase(offset).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _isLoading.value = false
                    _errorMessage.value = Empty_String
                    _marvelList.value =
                        MarvelListData(modelCharacterList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _isLoading.value = false
                    _errorMessage.value = result.message ?: Unexpected_Error
                }
                is Resource.Loading -> {
                    _errorMessage.value = Empty_String
                    _isLoading.value = true
                }
            }
        }.launchIn(viewModelScope)
    }

    companion object {
        const val Unexpected_Error = "Unexpected Error"
    }
}