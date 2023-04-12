package com.example.bih_coffee.presentation.drink_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bih_coffee.presentation.ScreenRoutes
import com.example.bih_coffee.presentation.drink_list.components.CoCoDrinkListItem


@Composable
fun DrinkListScreen(
    navController: NavController,
    viewModel: DrinkListViewModel = hiltViewModel()
) {

    // Updating the list of drinks when user comes back from detail screen
    LaunchedEffect(navController) {
        viewModel.retrieveDrinksFromDB()
    }

    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.drinks) { drink ->
                CoCoDrinkListItem(
                    drink = drink,
                    onItemClick = {
                        navController.navigate(
                            ScreenRoutes.DrinkDetailScreen.withArgs(drink.id)
                        )
                    }
                )

            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

}
