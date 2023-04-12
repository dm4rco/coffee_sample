package com.example.bih_coffee.presentation.drink_review

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bih_coffee.common.Status
import com.example.bih_coffee.data.repository.DrinkRepositoryImpl
import com.example.bih_coffee.domain.model.Review
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkReviewViewModel @Inject constructor(
    private val drinkRepository: DrinkRepositoryImpl,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf("")
    val state: State<String> = _state

    private val drinkId: Int = savedStateHandle["drinkId"] ?: -1

    val ratings = mutableListOf(
        "Temperature" to false,
        "Flavor" to false,
        "Aroma" to false,
        "Texture" to false,
        "Strength" to false,
        "Balance" to false,
        "Aftertaste" to false,
        "Presentation" to false,
        "Would recommend to friend" to false,
        "Would drink again" to false
    )

    fun updateRating(index: Int, item: Pair<String, Boolean>) {
        ratings[index] = item.copy(second = !item.second)
    }

    fun sendReview(name: String, reviewMessage: String, date: String) {
        val checkedRatings = ratings.filter { it.second }.map { it.first }
        val review = Review(
            id = drinkId,
            name = name,
            review = reviewMessage,
            date = date,
            ratings = checkedRatings.joinToString()
        )
        viewModelScope.launch {
            drinkRepository.sendReview(review).collect { result ->
                when (result) {
                    is Status.Error -> {
                        _state.value = result.exception.toString()
                    }
                    else -> {
                        // api does not exist so nothing else is ever expected
                    }
                }
            }
        }
    }
}