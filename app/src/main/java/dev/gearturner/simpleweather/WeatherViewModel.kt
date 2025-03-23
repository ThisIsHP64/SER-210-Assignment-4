package dev.gearturner.simpleweather

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gearturner.simpleweather.model.Town
import dev.gearturner.simpleweather.model.getTowns
import dev.gearturner.simpleweather.network.WeatherApi
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {
    var towns by mutableStateOf<List<Town>>(emptyList())

    fun loadWeather() {
        viewModelScope.launch {
            val filledTowns = getTowns().map { town ->
                val weather = WeatherApi.retrofitService.getWeather(
                    latitude = town.latitude,
                    longitude = town.longitude
                )
                town.copy(weather = weather)
            }
            towns = filledTowns
        }
    }
}