package com.example.bih_coffee.presentation.drink_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bih_coffee.R
import com.example.bih_coffee.domain.model.Drink


@Composable
fun CoCoDrinkListItem(
    drink: Drink,
    onItemClick: (Drink) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(drink) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = drink.title,
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )

        Text(text = stringResource(R.string.liked, drink.isLiked.toString()))
    }
}


@Preview()
@Composable
fun DefaultPreview() {
    CoCoDrinkListItem(
        drink = Drink(
            description = "Test",
            image = "test",
            ingredients = listOf("12,", "123"),
            title = "Americano"
        ),
        onItemClick = {}
    )
}