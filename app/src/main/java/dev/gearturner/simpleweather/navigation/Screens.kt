package dev.gearturner.simpleweather.navigation

enum class Screens {
    HomeScreen,
    DetailScreen,
    SettingsScreen,
    HelpScreen;

    companion object {
        fun fromRoute(route: String?): Screens
                = when(route?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            DetailScreen.name -> DetailScreen
            SettingsScreen.name -> SettingsScreen
            HelpScreen.name -> HelpScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}