// MainActivity.kt
package com.pridehotel.booking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel

import com.pridehotel.booking.ui.screens.HomeScreen
import com.pridehotel.booking.ui.theme.PrideHotelTheme
import com.pridehotel.booking.ui.viewmodels.HomeViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrideHotelTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home") },
                    selected = true,
                    onClick = { navController.navigate("home") }
                )
                // TODO: add Search, Bookings, Profile items here
            }
        }
    ) { innerPadding ->
        NavHost(
            navController    = navController,
            startDestination = "home",
            modifier         = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                val viewModel: HomeViewModel = hiltViewModel()
                HomeScreen(viewModel.hotels) { hotelId ->
                    // Navigate to Hotel Details
                }
            }
        }
    }
}
