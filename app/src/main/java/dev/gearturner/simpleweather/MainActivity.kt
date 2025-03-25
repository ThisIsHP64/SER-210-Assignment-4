/*
authors: Hunter Pageau (ThisIsHP64), Mohammed Fayed bin Salim (Fayed01428)
version: 23 Mar 2025
assignment: SER 210 Assignment 4
class for app entry point
 */

package dev.gearturner.simpleweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.gearturner.simpleweather.navigation.WeatherNavigation
import dev.gearturner.simpleweather.ui.theme.SimpleWeatherTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { // start app with ViewModel and the main WeatherNavigation composable
            val viewModel: WeatherViewModel = viewModel()
            App { WeatherNavigation(viewModel) }
        }
    }
}

@Composable
fun App(content: @Composable () -> Unit) {
    SimpleWeatherTheme {
        content()
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPreview() {
    val viewModel: WeatherViewModel = viewModel()
    App { WeatherNavigation(viewModel) }
}