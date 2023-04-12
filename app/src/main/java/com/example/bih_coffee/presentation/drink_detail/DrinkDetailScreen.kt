package com.example.bih_coffee.presentation.drink_detail

import com.example.bih_coffee.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.bih_coffee.presentation.ScreenRoutes

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DrinkDetailScreen(
    navController: NavController,
    viewModel: DrinkDetailViewModel = hiltViewModel()
) {

    val drink = viewModel.state.value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Text(
                    text = drink.title,
                    style = MaterialTheme.typography.h1
                )
                Button(
                    onClick = {
                        viewModel.onLikeClicked()
                    }
                ) {
                    Text(text = stringResource(R.string.liked, drink.isLiked.toString()))
                }
            }

            Divider()
        }
        item {
            GlideImage(
                modifier = Modifier.padding(bottom = 5.dp),
                model = drink.image,
                contentDescription = stringResource(R.string.picture, drink.title)
            )
            Text(
                text = stringResource(R.string.description, drink.description),
                style = MaterialTheme.typography.body1
            )
        }
        item {
            Text(
                text = stringResource(R.string.ingredient_list, drink.ingredients.joinToString()),
                style = MaterialTheme.typography.body2
            )
        }
        item {
            Button(
                onClick = {
                    navController.navigate(
                        ScreenRoutes.DrinkReviewScreen.withArgs(drink.id)
                    )
                }
            ) {
                Text(text = stringResource(R.string.write_review))
            }
        }
    }
}