package com.example.bih_coffee.presentation.drink_review

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bih_coffee.R
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DrinkReviewScreen(
    viewModel: DrinkReviewViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var review by remember { mutableStateOf(TextFieldValue("")) }
    var pickedDate by remember { mutableStateOf(LocalDate.now()) }

    val formattedDate = remember(pickedDate) {
        DateTimeFormatter.ofPattern("dd.MM.yyyy").format(pickedDate)
    }

    val dialogState = rememberMaterialDialogState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        item {
            OutlinedTextField(
                value = name,
                label = { Text(stringResource(R.string.enter_name)) },
                onValueChange = { name = it }
            )

            OutlinedTextField(
                modifier = Modifier.padding(vertical = 5.dp),
                value = review,
                label = { Text(stringResource(R.string.enter_review)) },
                onValueChange = { review = it }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = formattedDate)
                Button(onClick = { dialogState.show() }) {
                    Text(stringResource(R.string.change_date))
                }
            }

            MaterialDialog(
                dialogState = dialogState,
                buttons = {
                    positiveButton(text = stringResource(R.string.confirm))
                    negativeButton(text = stringResource(R.string.decline))
                }
            ) {
                datepicker(
                    initialDate = pickedDate,
                    title = stringResource(R.string.date_picker_title),
                    allowedDateValidator = { it.isBefore(LocalDate.now().plusDays(1)) }
                ) { date ->
                    pickedDate = date
                }
            }

            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = stringResource(R.string.list_of_likable_options)
            )
            viewModel.ratings.forEachIndexed { index, checkbox ->
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val isChecked = remember { mutableStateOf(checkbox.second) }
                    Checkbox(
                        checked = isChecked.value,
                        onCheckedChange = {
                            isChecked.value = it
                            viewModel.updateRating(index, checkbox)
                        },
                        enabled = true
                    )
                    Text(text = checkbox.first)
                }
            }

            Button(
                modifier = Modifier.padding(vertical = 5.dp),
                onClick = {
                    viewModel.sendReview(
                        name = name.text,
                        reviewMessage = review.text,
                        date = pickedDate.toString()
                    )
                }
            ) {
                Text(stringResource(R.string.send_review))
            }
        }


    }
    if (state.isNotBlank()) {
        Toast.makeText(
            LocalContext.current,
            state,
            Toast.LENGTH_LONG
        ).show()
    }
}