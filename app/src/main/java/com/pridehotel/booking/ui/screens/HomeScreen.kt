package com.pridehotel.booking.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.pridehotel.booking.data.models.Hotel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    hotels: List<Hotel>,
    onHotelClick: (String) -> Unit
) {
    val carouselImages = listOf(
        "https://assets.simplotel.com/simplotel/image/upload/x_0,y_0,w_2858,h_1608,r_0,c_crop,q_80,fl_progressive/w_1650,c_fit,f_auto/pride-hotels-group/Empowering-Women-banner_(1)_f7b24f7c",
        "https://assets.simplotel.com/simplotel/image/upload/x_0,y_0,w_3500,h_1969,r_0,c_crop,q_80,fl_progressive/w_1650,c_fit,f_auto/pride-hotels-group/wedding-with-pride_e41de40a",
        "https://assets.simplotel.com/simplotel/image/upload/x_0,y_0,w_3500,h_1969,r_0,c_crop,q_80,fl_progressive/w_1650,c_fit,f_auto/pride-hotels-group/gandhinagar-banner_8010f10c",
        "https://assets.simplotel.com/simplotel/image/upload/x_0,y_0,w_3500,h_1969,r_0,c_crop,q_80,fl_progressive/w_1650,c_fit,f_auto/pride-hotels-group/ahemdabad-banner_a16d6d6e",
        "https://assets.simplotel.com/simplotel/image/upload/x_0,y_0,w_3500,h_1969,r_0,c_crop,q_80,fl_progressive/w_1650,c_fit,f_auto/pride-hotels-group/Bengaluru-cityscape-banner_(1)_d4f56fd0",
        "https://assets.simplotel.com/simplotel/image/upload/x_-2,y_138,w_3502,h_1741,r_0,c_crop,q_80,fl_progressive/w_2475,f_auto,c_fit/pride-hotels-group/advantages-image_hbozet"
    )
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            while (true) {
                delay(3000)
                pagerState.animateScrollToPage((pagerState.currentPage + 1) % carouselImages.size)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.background
                    )
                )
            )
            .padding(16.dp)
    ) {
        HorizontalPager(
            count = carouselImages.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .clip(RoundedCornerShape(12.dp))
        ) { page ->
            AsyncImage(
                model = carouselImages[page],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Our Hotels",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            contentPadding = PaddingValues(horizontal = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            items(hotels) { hotel ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .width(160.dp)
                        .clickable { onHotelClick(hotel.id) }
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = hotel.imageUrl,
                            contentDescription = hotel.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp))
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = hotel.name,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}