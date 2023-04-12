package com.example.bih_coffee.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bih_coffee.data.local.entity.DrinkEntity

@Database(
    entities = [DrinkEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class DrinkDatabase : RoomDatabase(){

    abstract val dao: DrinkDao
}