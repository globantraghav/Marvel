package com.example.marvel.presentation.character_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.common.Resource
import com.example.marvel.domain.use_cases.CharacterDetailsUseCase
import com.example.marvel.presentation.character_list.MarvelListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val characterDetailsUseCase: CharacterDetailsUseCase
) :ViewModel() {

    private val _characterDetails = MutableLiveData(CharacterDetailState())
    val characterDetails : LiveData<CharacterDetailState> = _characterDetails

     fun getCharacterDetails(id:Int){
        characterDetailsUseCase(id).onEach {result->

            when(result){
                is Resource.Success->{
                    _characterDetails.value = CharacterDetailState(characterDetails = result.data)
                }
                is Resource.Error->{
                    _characterDetails.value = CharacterDetailState(error = result.message?:"Unexpected Error")
                }
                is Resource.Loading->{
                    _characterDetails.value = CharacterDetailState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
}