package com.example.bih_coffee.data.local

import androidx.room.*
import com.example.bih_coffee.data.local.entity.DrinkEntity

@Dao
interface DrinkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDrinks(drinks: List<DrinkEntity>)

    @Query("SELECT * FROM drinkentity")
    suspend fun getDrinks(): List<DrinkEntity>

    @Query("SELECT * FROM drinkentity WHERE databaseId=:id LIMIT 1")
    suspend fun getDrinkById(id: Int): DrinkEntity

    @Query("UPDATE drinkentity SET isLiked=:isLiked WHERE databaseId=:id")
    suspend fun updateLikeStatusById(isLiked: Boolean, id: Int)

    @Query("DELETE FROM drinkentity")
    suspend fun deleteAll()
}