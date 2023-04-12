package com.example.bih_coffee.domain.model

data class Drink(
    val id: Int = -1,
    val description: String = "",
    val image: String = "",
    val ingredients: List<String> = emptyList(),
    val title: String = "",
    val isLiked: Boolean = false
)