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
        setContent {
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