package com.example.bih_coffee.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bih_coffee.domain.model.Drink

@Entity
data class DrinkEntity(
    val description: String,
    val image: String,
    val ingredients: List<String>,
    val title: String,
    val isLiked: Boolean? = null,
    @PrimaryKey val databaseId: Int
) {
    fun toDrink(): Drink {
        return Drink(
            id = databaseId,
            description = description,
            image = image,
            ingredients = ingredients,
            title = title,
            isLiked = isLiked ?: false
        )
    }
}
