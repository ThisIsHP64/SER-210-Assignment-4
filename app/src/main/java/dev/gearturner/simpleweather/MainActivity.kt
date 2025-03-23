package dev.gearturner.simpleweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.gearturner.simpleweather.model.getTowns
import dev.gearturner.simpleweather.screens.Details
import dev.gearturner.simpleweather.screens.HomeScreen
import dev.gearturner.simpleweather.screens.Screen
import dev.gearturner.simpleweather.ui.theme.SimpleWeatherTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleWeatherTheme {
                WeatherApp()
            }
        }
    }
}

@Composable
fun WeatherApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument("townName") { type = NavType.StringType })
        ) { backStackEntry ->
            val townName = backStackEntry.arguments?.getString("townName")
            val town = getTowns().find{it.name == townName}

            if (town != null) {
                Details(town = town)
            } else {
                Text("Town not found", modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center)
            }
        }
    }
}

@Preview
@Composable
fun preNav() {
    WeatherApp()
}

