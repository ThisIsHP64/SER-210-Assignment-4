package dev.gearturner.simpleweather.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.gearturner.simpleweather.R

@Composable
fun HelpScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Help and Info", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("SimpleWeather uses the OpenMeteo API, a free-to-use weather API that provides accurate real-time weather data.")
        Spacer(modifier = Modifier.height(16.dp))
        Text("To see current weather, just choose a town! To change the background color, go to Menu -> Settings.")
    }
}