/*
authors: Hunter Pageau (ThisIsHP64), Mohammed Fayed bin Salim (Fayed01428)
version: 23 Mar 2025
assignment: SER 210 Assignment 4
auto-generated weather data class from forecast endpoint json, generated from JsonToKotlinClass plugin
 */

package dev.gearturner.simpleweather.model

data class Weather(
    val current: Current,
    val current_units: CurrentUnits,
    val daily: Daily,
    val daily_units: DailyUnits,
    val elevation: Double,
    val generationtime_ms: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)