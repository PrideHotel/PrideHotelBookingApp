package com.pridehotel.booking

import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import com.pridehotel.booking.R
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
fun HeroVideo(modifier: Modifier = Modifier) {
    val ctx = LocalContext.current
    Box(modifier = modifier) {
        // Static placeholder behind the VideoView
        Image(
            painter = painterResource(R.drawable.placeholder_hero),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )
        AndroidView(factory = {
            VideoView(it).apply {
                setVideoURI(
                    Uri.parse("android.resource://${it.packageName}/${R.raw.placeholder_video}")
                )
                setOnPreparedListener { mp ->
                    mp.isLooping = true
                    start()
                }
                setOnErrorListener { _, _, _ -> true }
            }
        }, modifier = Modifier.matchParentSize())
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                listOf(
                    "home" to Icons.Filled.Home,
                    "search" to Icons.Filled.Search,
                    "bookings" to Icons.Filled.Book,
                    "profile" to Icons.Filled.AccountCircle
                ).forEach { (route, icon) ->
                    NavigationBarItem(
                        icon = { Icon(icon, contentDescription = route.capitalize()) },
                        label = { Text(route.capitalize()) },
                        selected = currentRoute(navController) == route,
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
                navController    = navController,
                startDestination = "home",
                modifier         = Modifier.weight(1f)
            ) {
                composable("home") {
                    val vm: HomeViewModel = hiltViewModel()
                    HomeScreen(vm.hotels) { /* TODO: Details */ }
                }
                composable("search")   { /* TODO: Search screen */ }
                composable("bookings") { /* TODO: Bookings screen */ }
                composable("profile")  { /* TODO: Profile screen */ }
            }
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val backStack by navController.currentBackStackEntryAsState()
    return backStack?.destination?.route
}
