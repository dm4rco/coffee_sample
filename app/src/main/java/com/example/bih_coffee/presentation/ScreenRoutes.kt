package com.example.bih_coffee.presentation

sealed class ScreenRoutes(val route: String) {
    object DrinkListScreen : ScreenRoutes("drink_list")
    object DrinkDetailScreen : ScreenRoutes("drink_detail")
    object DrinkReviewScreen : ScreenRoutes("drink_review")

    fun withArgs(vararg args: Int): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}