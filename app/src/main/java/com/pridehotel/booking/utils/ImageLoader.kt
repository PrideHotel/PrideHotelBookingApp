package com.pridehotel.booking.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun LoadImage(url: String, contentDescription: String?, modifier: Modifier = Modifier) {
    AsyncImage(
        model = url,
        contentDescription = contentDescription,
        modifier = modifier
    )
}