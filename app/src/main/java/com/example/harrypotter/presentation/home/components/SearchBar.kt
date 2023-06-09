package com.example.harrypotter.presentation.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.Tune
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    onPopBackStack: () -> Unit,
    onFilterClicked: () -> Unit,
    onLeadingIconClicked:() -> Unit,
    onSearch: () -> Unit,
    onReset: () -> Unit,
    value: String,
    onValueChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .padding(5.dp)
            .fillMaxWidth()
        ,
        value = value,
        placeholder = { Text(text = "search here ...") },
        onValueChange = { onValueChange(it) },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colorScheme.tertiary,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch()
                keyboardController?.hide()
            }
        ),
        leadingIcon = {
            IconButton(onClick = { onLeadingIconClicked() }) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "back")
            }
        },
        trailingIcon = {
            if (value.isEmpty()){
                IconButton(onClick = { onFilterClicked() }) {
                    Icon(imageVector = Icons.Rounded.Tune, contentDescription ="" )
                }
            } else {
                IconButton(
                    onClick = {
                        if (value.isNotEmpty()){
                            onValueChange("")
                            onReset()
                        } else {
                            onPopBackStack()
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Rounded.Cancel, contentDescription = "cancel")
                }
            }
        }
    )
}