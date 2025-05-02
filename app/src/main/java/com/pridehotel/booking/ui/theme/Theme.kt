package com.pridehotel.booking.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.pridehotel.booking.R

private val ColorScheme = lightColorScheme(
    primary = Color(0xFF2A2A72), // Dark blue (assumed branding)
    secondary = Color(0xFFBE863C), // Gold from theme-color
    background = Color.White
)

private val Mulish = FontFamily(
    Font(R.font.mulish_regular, FontWeight.Normal),
    Font(R.font.mulish_bold, FontWeight.Bold)
)

private val PlayfairDisplay = FontFamily(
    Font(R.font.playfair_display_regular, FontWeight.Normal),
    Font(R.font.playfair_display_bold, FontWeight.Bold)
)

@Composable
fun PrideHotelTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = MaterialTheme.typography.copy(
            bodyLarge = MaterialTheme.typography.bodyLarge.copy(fontFamily = Mulish),
            headlineSmall = MaterialTheme.typography.headlineSmall.copy(fontFamily = PlayfairDisplay)
        ),
        content = content
    )
}