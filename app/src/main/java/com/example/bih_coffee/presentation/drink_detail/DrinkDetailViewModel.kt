package com.example.bih_coffee.presentation.drink_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bih_coffee.common.Status
import com.example.bih_coffee.data.repository.DrinkRepositoryImpl
import com.example.bih_coffee.domain.model.Drink
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkDetailViewModel @Inject constructor(
    private val drinkRepository: DrinkRepositoryImpl,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(Drink())
    val state: State<Drink> = _state

    private val drinkId: Int = savedStateHandle["drinkId"] ?: -1

    init {
        getDrink(drinkId)
    }

    private fun getDrink(drinkId: Int) {
        viewModelScope.launch {
            drinkRepository.getDrinkById(drinkId).collect { result ->
                when (result) {
                    is Status.Success -> {
                        _state.value = result.data ?: Drink()
                    }
                    else -> {}
                }
            }
        }
    }

    fun onLikeClicked() {
        _state.value = _state.value.copy(
            isLiked = !_state.value.isLiked
        )
        viewModelScope.launch {
            drinkRepository.updateLikeStatusById(
                isLiked = _state.value.isLiked,
                id = _state.value.id
            )
        }
    }

}