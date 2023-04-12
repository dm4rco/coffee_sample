package com.example.bih_coffee.data.remote.dto

import com.example.bih_coffee.data.local.entity.DrinkEntity

data class DrinkDto(
    val description: String,
    val id: Int,
    val image: String,
    val ingredients: List<String>,
    val title: String
) {
    fun toDrinkEntity(isLiked: Boolean? = false): DrinkEntity {
        return DrinkEntity(
            databaseId = id,
            description = description,
            image = image,
            ingredients = ingredients,
            title = title,
            isLiked = isLiked
        )
    }
}






