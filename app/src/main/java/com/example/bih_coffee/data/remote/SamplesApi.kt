package com.example.bih_coffee.data.remote

import com.example.bih_coffee.data.remote.dto.DrinkDto
import com.example.bih_coffee.domain.model.Review
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SamplesApi {

    @GET("/coffee/hot")
    suspend fun getDrinks(): List<DrinkDto>

    @POST("/review/{drinkId}")
    suspend fun sendReview(@Body review: Review)
}