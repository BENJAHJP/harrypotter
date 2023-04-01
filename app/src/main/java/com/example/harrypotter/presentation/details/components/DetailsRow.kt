package com.example.harrypotter.presentation.details.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun DetailsRow(
    title: String,
    value: Any
) {
    Row() {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = value.toString(),
            fontWeight = FontWeight.Bold,
        )
    }
}