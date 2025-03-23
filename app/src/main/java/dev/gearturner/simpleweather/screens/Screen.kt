package dev.gearturner.simpleweather.screens

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    object Home : Screen("home")

    object Details : Screen("details/{townName}") {
        fun createRoute(townName: String) = "details/${townName}"

        val arguments = listOf(
            navArgument("townName") { type = NavType.StringType }
        )

    }
}