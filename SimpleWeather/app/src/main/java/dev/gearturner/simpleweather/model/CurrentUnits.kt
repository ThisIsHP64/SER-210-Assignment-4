package dev.gearturner.simpleweather.model

data class CurrentUnits(
    val interval: String,
    val precipitation: String,
    val relative_humidity_2m: String,
    val temperature_2m: String,
    val time: String,
    val wind_direction_10m: String,
    val wind_speed_10m: String
)