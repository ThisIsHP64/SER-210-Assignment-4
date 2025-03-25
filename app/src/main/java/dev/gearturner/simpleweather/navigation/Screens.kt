/*
authors: Hunter Pageau (ThisIsHP64), Mohammed Fayed bin Salim (Fayed01428)
version: 23 Mar 2025
assignment: SER 210 Assignment 4
enum class of all screens
 */

package dev.gearturner.simpleweather.navigation

enum class Screens {
    HomeScreen,
    DetailScreen,
    SettingsScreen,
    HelpScreen;

    // handle routes to make navigation easier
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