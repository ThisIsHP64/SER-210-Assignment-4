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
import dev.gearturner.simpleweather.model.Town
import dev.gearturner.simpleweather.model.getTowns
import kotlin.math.roundToInt

@Composable
fun Details(town: Town) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
            val weather = town.weather
            if (weather != null) {
                val current = weather.current
                val currentUnits = weather.current_units
                val daily = weather.daily
                val dailyUnits = weather.daily_units

                val currentTemp =
                    "${current.temperature_2m.roundToInt()}${currentUnits.temperature_2m}"
                val highTemp = daily.temperature_2m_max.firstOrNull()?.roundToInt() ?: 0
                val lowTemp = daily.temperature_2m_min.firstOrNull()?.roundToInt() ?: 0

                val windSpeed =
                    "${current.wind_speed_10m.roundToInt()}${currentUnits.wind_speed_10m}"
                val windDirection =
                    "${current.wind_direction_10m}${currentUnits.wind_direction_10m}"

                val humidity = "${current.relative_humidity_2m}${currentUnits.relative_humidity_2m}"
                val precipitation = "${current.precipitation}${currentUnits.precipitation}"

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.LightGray)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {

                        Text(text = town.name, fontSize = 24.sp)

                        Text(text = currentTemp, fontSize = 24.sp)

                        Row(modifier = Modifier) {
                            Text(
                                text = "H: $highTemp${dailyUnits.temperature_2m_max}",
                                fontSize = 24.sp
                            )

                            Spacer(
                                modifier = Modifier
                                    .width(10.dp)
                            )

                            Text(
                                text = "L: $lowTemp${dailyUnits.temperature_2m_min}",
                                fontSize = 24.sp
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.LightGray)
                ) {
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
    }
