package com.example.bih_coffee.presentation.drink_list

import com.example.bih_coffee.domain.model.Drink

data class DrinkListState(
    val isLoading: Boolean = false,
    val drinks: List<Drink> = emptyList(),
    val error: String = ""
)
