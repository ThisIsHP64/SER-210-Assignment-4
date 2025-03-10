package dev.gearturner.simpleweather.model

data class Town(
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val weather: Weather? = null
)

fun getTowns(): List<Town> {
    return listOf<Town>(
        Town(
            name = "Hamden",
            latitude = 41.3959,
            longitude = -72.8968,
        ),
        Town(
            name = "North Haven",
            latitude = 41.3909,
            longitude = -72.8595,
        ),
        Town(
            name = "New Haven",
            latitude = 41.3081,
            longitude = -72.9282,
        ),
        Town(
            name = "New London",
            latitude = 41.3556,
            longitude = -72.0995,
        ),
        Town(
            name = "Meriden",
            latitude = 41.5382,
            longitude = -72.807,
        ),
        Town(
            name = "Hartford",
            latitude = 41.7637,
            longitude = -72.6851,
        ),
        Town(
            name = "Tolland",
            latitude = 41.8715,
            longitude = -72.3687,
        ),
        Town(
            name = "Bridgeport",
            latitude = 41.1792,
            longitude = -73.1894,
        )
    )
}