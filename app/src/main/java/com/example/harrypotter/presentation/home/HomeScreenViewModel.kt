package com.example.harrypotter.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.harrypotter.common.Resource
import com.example.harrypotter.domain.use_case.GetAllCharactersUseCase
import com.example.harrypotter.domain.use_case.GetCharacterByHouseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val getCharacterByHouseUseCase: GetCharacterByHouseUseCase
):ViewModel(){

    var houseName by mutableStateOf("")
    var characterName by mutableStateOf("")
    init {
        getCharacters()
    }

    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> = _state

    fun getCharacters(){
        getAllCharactersUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = HomeScreenState(characters = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = HomeScreenState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = HomeScreenState(message = result.message ?:"")
                }
            }
        }
    }

    fun getCharactersByHouseName(){
        getCharacterByHouseUseCase(houseName).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = HomeScreenState(characters = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = HomeScreenState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = HomeScreenState(message = result.message ?:"")
                }
            }
        }
    }
}