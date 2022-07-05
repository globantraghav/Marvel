package com.example.marvel.presentation.character_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.common.Resource
import com.example.marvel.domain.use_cases.CharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val characterListUseCase: CharacterListUseCase
):ViewModel() {

    private val _marvelList = MutableLiveData(MarvelListData())
    val marvelList : LiveData<MarvelListData> = _marvelList

    fun getCharacters(offset:Int){
        characterListUseCase(offset).onEach {result->
            when(result){
                is Resource.Success->{
                    _marvelList.value = MarvelListData(characterList = result.data?: emptyList())
                }
                is Resource.Error->{
                    _marvelList.value = MarvelListData(error = result.message?:"Unexpected Error")
                }
                is Resource.Loading->{
                    _marvelList.value = MarvelListData(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}