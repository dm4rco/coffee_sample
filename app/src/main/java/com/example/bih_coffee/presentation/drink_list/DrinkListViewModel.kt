package com.example.bih_coffee.presentation.drink_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bih_coffee.common.Status
import com.example.bih_coffee.data.repository.DrinkRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DrinkListViewModel @Inject constructor(
    private val drinkRepository: DrinkRepositoryImpl,
) : ViewModel() {

    private val _state = mutableStateOf(DrinkListState())
    val state: State<DrinkListState> = _state

    init {
        getDrinks()
    }

    fun retrieveDrinksFromDB() {
        drinkRepository.getDrinks(onlyDatabase = true).onEach { result ->
            when (result) {
                is Status.Success -> {
                    _state.value = DrinkListState(drinks = result.data ?: emptyList())
                }
                is Status.Error -> {
                    _state.value = DrinkListState(
                        error = result.exception.toString()
                    )
                }
                is Status.Loading -> {
                    _state.value = DrinkListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getDrinks() {
        drinkRepository.getDrinks().onEach { result ->
            when (result) {
                is Status.Success -> {
                    _state.value = DrinkListState(drinks = result.data ?: emptyList())
                }
                is Status.Error -> {
                    _state.value = DrinkListState(
                        error = result.exception.toString()
                    )
                }
                is Status.Loading -> {
                    _state.value = DrinkListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}