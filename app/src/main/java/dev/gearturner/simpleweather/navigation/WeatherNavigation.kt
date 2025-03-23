package dev.gearturner.simpleweather.navigation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.gearturner.simpleweather.WeatherViewModel
import dev.gearturner.simpleweather.model.getTowns
import dev.gearturner.simpleweather.screens.HomeScreen
import dev.gearturner.simpleweather.screens.DetailScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier
) {
    TopAppBar(
        title = { Text("SimpleWeather") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        modifier = modifier,
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Composable
fun WeatherNavigation(viewModel: WeatherViewModel) {
    val navController = rememberNavController()
    val viewModel = viewModel
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screens.fromRoute(currentBackStackEntry?.destination?.route)
    val canNavigateBack = currentScreen != Screens.HomeScreen

    Scaffold(
        topBar = {
            NavBar(
                canNavigateBack = canNavigateBack,
                navigateUp = { navController.navigateUp() },
                modifier = Modifier
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.HomeScreen.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(Screens.HomeScreen.name) {
                HomeScreen(navController = navController)
            }

            composable("${Screens.DetailScreen.name}/{townName}") { backStackEntry ->
                val townName = backStackEntry.arguments?.getString("townName")
                if(viewModel.towns.isEmpty()) {
                    viewModel.loadWeather()
                }
                val town = viewModel.towns.find { it.name == townName }
                if(town != null) {
                    Log.d("weatherload", town.weather.toString())
                    DetailScreen(town, navController)
                } else {
                    Text("Loading...")
                }
            }
        }

    }
}