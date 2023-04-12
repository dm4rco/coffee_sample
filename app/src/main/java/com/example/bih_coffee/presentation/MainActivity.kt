package com.example.bih_coffee.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bih_coffee.presentation.drink_detail.DrinkDetailScreen
import com.example.bih_coffee.presentation.drink_list.DrinkListScreen
import com.example.bih_coffee.presentation.drink_review.DrinkReviewScreen
import com.example.bih_coffee.presentation.ui.theme.BiH_CoffeeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BiH_CoffeeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }

    }
}

@Composable
fun Navigation() {
    val ctx = LocalContext.current
    val navController = rememberNavController()

    val navArgumentId = listOf(
        navArgument("drinkId") {
            type = NavType.IntType
            nullable = false
        }
    )

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.DrinkListScreen.route
    ) {
        composable(route = ScreenRoutes.DrinkListScreen.route) {
            DrinkListScreen(navController = navController)
        }
        composable(
            route = ScreenRoutes.DrinkDetailScreen.route + "/{drinkId}",
            arguments = navArgumentId
        ) { navBackStackEntry ->
            val drinkId = navBackStackEntry.arguments?.getInt("drinkId")
            if (drinkId == null) {
                Toast.makeText(ctx, "Something went wrong", Toast.LENGTH_LONG).show()
            } else {
                DrinkDetailScreen(navController = navController)
            }
        }
        composable(
            route = ScreenRoutes.DrinkReviewScreen.route + "/{drinkId}",
            arguments = navArgumentId
        ) { navBackStackEntry ->
            val drinkId = navBackStackEntry.arguments?.getInt("drinkId")
            if (drinkId == null) {
                Toast.makeText(ctx, "Something went wrong", Toast.LENGTH_LONG).show()
            } else {
                DrinkReviewScreen()
            }
        }
    }
}