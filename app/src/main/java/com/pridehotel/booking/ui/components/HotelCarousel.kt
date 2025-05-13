package com.pridehotel.booking.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HotelCarousel(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()
    val images = listOf(
        "https://assets.simplotel.com/simplotel/image/upload/pride-hotels-group/wedding-with-pride_e41de40a",
        "https://assets.simplotel.com/simplotel/image/upload/pride-plaza-ahmedabad/1_(1)_fd98666c",
        "https://assets.simplotel.com/simplotel/image/upload/pride-hotel-bengaluru/Hotel_Exterior_Bldg_qzx0kh"
    )

    HorizontalPager(
        count = images.size,
        state = pagerState,
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 8.dp)
    ) { page ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(4.dp)
        ) {
            Text(
                text = "Image ${page + 1}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
