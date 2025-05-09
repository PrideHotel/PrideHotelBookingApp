// MainActivity.kt
package com.pridehotel.booking

import android.os.Bundle
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.VolumeOff
import androidx.compose.material.icons.automirrored.filled.VolumeUp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import com.pridehotel.booking.ui.screens.HomeScreen
import com.pridehotel.booking.ui.theme.PrideHotelTheme
import com.pridehotel.booking.ui.viewmodels.HomeViewModel
import androidx.core.net.toUri

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
fun HeroVideo(
    modifier: Modifier = Modifier
) {
    val ctx = LocalContext.current
    var isAudioOn by remember { mutableStateOf(true) }
    var mediaPlayer by remember { mutableStateOf<android.media.MediaPlayer?>(null) }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopEnd
    ) {
        AndroidView(
            factory = {
                VideoView(it).apply {
                    setVideoURI("android.resource://${it.packageName}/${R.raw.placeholder_video}".toUri())
                    setOnPreparedListener { mp ->
                        mediaPlayer = mp
                        mp.isLooping = true
                        mp.setVolume(1f, 1f)
                        start()
                    }
                    setOnErrorListener { _, _, _ -> true }
                }
            },
            modifier = Modifier.matchParentSize()
        )
        IconButton(
            onClick = {
                isAudioOn = !isAudioOn
                mediaPlayer?.setVolume(if (isAudioOn) 1f else 0f, if (isAudioOn) 1f else 0f)
            },
            modifier = Modifier
                .padding(8.dp)
                .size(32.dp)
                .background(Color.Black.copy(alpha = 0.5f), shape = MaterialTheme.shapes.small)
        ) {
            Icon(
                imageVector = if (isAudioOn) Icons.AutoMirrored. Filled. VolumeUp else Icons.AutoMirrored. Filled. VolumeOff,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                val items = listOf(
                    "home" to Icons.Default.Home,
                    "search" to Icons.Default.Search,
                    "bookings" to Icons.Default.Book,
                    "profile" to Icons.Default.AccountCircle
                )
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                items.forEach { (route, icon) ->
                    NavigationBarItem(
                        icon = { Icon(icon, contentDescription = null) },
                        label = { Text(route.replaceFirstChar { it.uppercase() }) },
                        selected = currentRoute == route,
                        onClick = { navController.navigate(route) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            HeroVideo(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.weight(1f)
            ) {
                composable("home") {
                    val vm: HomeViewModel = hiltViewModel()
                    HomeScreen(vm.hotels) { /* Details */ }
                }
                composable("search") { /* Search */ }
                composable("bookings") { /* Bookings */ }
                composable("profile") { /* Profile */ }
            }
        }
    }
}