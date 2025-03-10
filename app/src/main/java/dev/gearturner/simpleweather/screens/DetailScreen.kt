package dev.gearturner.simpleweather.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Details(townName: String, currentTemp: String, highestTemp: Int, lowestTemp: Int,
            windSpeed: String, windDirection: String, humidity: Double, precipitation: Double) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray)
        ){
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally)
            {

                Text(text = townName, fontSize = 24.sp)

                Text(text = currentTemp, fontSize = 24.sp)

                Row(modifier = Modifier) {
                    Text(text = "H: $highestTemp", fontSize = 24.sp)

                    Spacer(modifier = Modifier
                        .width(10.dp))

                    Text(text = "L: $lowestTemp", fontSize = 24.sp)
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Wind speed: $windSpeed", fontSize = 24.sp)

                Text(text = "Wind direction: $windDirection", fontSize = 24.sp)

                Text(text = "Humidity: $humidity", fontSize = 24.sp)

                Text(text = "Precipitation: $precipitation", fontSize = 24.sp)
            }
        }
    }
}


@Preview
@Composable
fun previewDetails() {
    Details("Hamen","40*C", 49, 33, "20mph",
        "South", 30.2, 20.8)
}