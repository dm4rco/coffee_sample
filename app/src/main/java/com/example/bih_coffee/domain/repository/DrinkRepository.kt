package com.example.bih_coffee.domain.repository

import com.example.bih_coffee.domain.model.Drink
import kotlinx.coroutines.flow.Flow
import com.example.bih_coffee.common.Status
import com.example.bih_coffee.domain.model.Review

interface DrinkRepository {

    fun getDrinks(onlyDatabase: Boolean = false): Flow<Status<List<Drink>>>

    fun getDrinkById(id: Int): Flow<Status<Drink>>

    suspend fun updateLikeStatusById(isLiked: Boolean, id: Int)

    suspend fun sendReview(review: Review): Flow<Status<Any>>
}