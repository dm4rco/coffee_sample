package com.example.bih_coffee.di

import android.app.Application
import androidx.room.Room
import com.example.bih_coffee.common.Constants
import com.example.bih_coffee.data.local.Converters
import com.example.bih_coffee.data.local.DrinkDatabase
import com.example.bih_coffee.data.remote.SamplesApi
import com.example.bih_coffee.data.repository.DrinkRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDrinkRepository(
        api: SamplesApi,
        db: DrinkDatabase
    ): DrinkRepositoryImpl {
        return DrinkRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideSamplesApi(): SamplesApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SamplesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDrinkDatabase(app: Application): DrinkDatabase {
        return Room.databaseBuilder(
            app,
            DrinkDatabase::class.java,
            "drinks_db"
        )
            .addTypeConverter(Converters())
            .build()
    }
}