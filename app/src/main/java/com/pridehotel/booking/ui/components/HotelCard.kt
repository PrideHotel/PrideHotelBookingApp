package com.pridehotel.booking.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pridehotel.booking.data.models.Hotel
import com.pridehotel.booking.utils.LoadImage

@Composable
fun HotelCard(hotel: Hotel, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column {
            LoadImage(
                url               = hotel.imageUrl,
                contentDescription = hotel.name,
                modifier          = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            )
            Text(
                text  = hotel.name,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(text = hotel.location)
            Button(onClick = onClick) {
                Text("View Details")
            }
        }
    }
}
