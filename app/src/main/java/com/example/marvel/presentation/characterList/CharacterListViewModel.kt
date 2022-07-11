package com.example.marvel.presentation.characterList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.Constant
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
    val marvelList: LiveData<MarvelListData> = _marvelList

    fun getCharactersList(offset: Int) {
        characterListUseCase(offset).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _marvelList.value =
                        MarvelListData(modelCharacterList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _marvelList.value =
                        MarvelListData(error = result.message ?: Unexpected_Error)
                }
                is Resource.Loading -> {
                    _marvelList.value = MarvelListData(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    companion object{
        const val Unexpected_Error = "Unexpected Error"
    }
}