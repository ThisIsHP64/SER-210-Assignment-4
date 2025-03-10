package dev.gearturner.simpleweather.model

data class Current(
    val interval: Int,
    val precipitation: Double,
    val relative_humidity_2m: Int,
    val temperature_2m: Double,
    val time: String,
    val wind_direction_10m: Int,
    val wind_speed_10m: Double
)