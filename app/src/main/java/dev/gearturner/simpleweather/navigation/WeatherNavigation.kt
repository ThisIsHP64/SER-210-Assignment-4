/*
authors: Hunter Pageau (ThisIsHP64), Mohammed Fayed bin Salim (Fayed01428)
version: 23 Mar 2025
assignment: SER 210 Assignment 4
functions to handle navigation, implement TopAppBar, contain app body
 */

package dev.gearturner.simpleweather.navigation

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.gearturner.simpleweather.WeatherViewModel
import dev.gearturner.simpleweather.screens.DetailScreen
import dev.gearturner.simpleweather.screens.HelpScreen
import dev.gearturner.simpleweather.screens.HomeScreen
import dev.gearturner.simpleweather.screens.SettingsScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    onMenuClick: (() -> Unit)? = null,
    containerColor: Color,
    modifier: Modifier,
    onShare: (() -> Unit)? = null
) {
    TopAppBar(
        title = { Text(
            text = "SimpleWeather",
            color = MaterialTheme.colorScheme.onSecondary
        ) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = containerColor
        ),
        actions = { // share button
            if(onShare != null) {
                IconButton(onClick = onShare) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        tint = MaterialTheme.colorScheme.onSecondary,
                        contentDescription = null
                    )
                }
            }
        },
        modifier = modifier,
        navigationIcon = { // show back button if not on HomeScreen
            if(canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = MaterialTheme.colorScheme.onSecondary,
                        contentDescription = null
                    )
                }
            } else if(onMenuClick != null) { // hamburger menu otherwise
                IconButton(onClick = onMenuClick) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        tint = MaterialTheme.colorScheme.onSecondary,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

// navigation ui and logic
@Composable
fun WeatherNavigation(viewModel: WeatherViewModel) {
    // track navigation and variables
    val navController = rememberNavController()
    val viewModel = viewModel
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screens.fromRoute(currentBackStackEntry?.destination?.route)
    val canNavigateBack = currentScreen != Screens.HomeScreen
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // background color change
    val defaultNavBarColor = MaterialTheme.colorScheme.secondary
    var navBarColor by remember { mutableStateOf(defaultNavBarColor) }
    val toggleBackgroundColor = {
        navBarColor = if(navBarColor == defaultNavBarColor)
            Color(red = 112f / 255f, green = 50f / 255f, blue = 50f / 255f)
        else
            defaultNavBarColor
    }

    // share prerequisites
    var shareText by remember { mutableStateOf("") }
    val context = LocalContext.current

    // Scaffold that surrounds the screens, contains TopAppBar
    val scaffoldBody = @Composable {
        Scaffold(
            topBar = {
                NavBar(
                    canNavigateBack = canNavigateBack,
                    navigateUp = { navController.navigateUp() },
                    modifier = Modifier,
                    onMenuClick = { scope.launch { drawerState.open() } },

                    // share logic
                    onShare = if(currentScreen == Screens.DetailScreen) {
                        {
                            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                                putExtra(Intent.EXTRA_TEXT, shareText)
                                type = "text/plain"
                            }
                            val chooser = Intent.createChooser(sendIntent, "Share weather info with...")
                            context.startActivity(chooser)
                        }

                    } else null,
                    containerColor = navBarColor
                )
            }
        ) { innerPadding ->
            // navigation logic
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

                // go to appropriate DetailScreen based on route
                composable("${Screens.DetailScreen.name}/{townName}") { backStackEntry ->
                    val townName = backStackEntry.arguments?.getString("townName")
                    if(viewModel.towns.isEmpty()) {
                        viewModel.loadWeather()
                    }
                    val town = viewModel.towns.find { it.name == townName }
                    if(town != null) {
                        shareText = town.weather.toString()
                        DetailScreen(town, navController)
                    } else {
                        Text("Loading...") // display loading message while waiting for API to respond
                    }
                }

                composable(Screens.SettingsScreen.name) {
                    SettingsScreen(toggleBgColor = toggleBackgroundColor)
                }

                composable(Screens.HelpScreen.name) {
                    HelpScreen()
                }
            }
        }
    }

    // hamburger menu content, only shown if on HomeScreen
    if(currentScreen == Screens.HomeScreen) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                Column(modifier = Modifier
                    .fillMaxHeight()
                    .width(300.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(top = 48.dp, end = 8.dp)
                ) {
                    Text(
                        text = "Home",
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable {
                                navController.navigate(Screens.HomeScreen.name)
                                scope.launch { drawerState.close() }
                            }
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = "Settings",
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable {
                                navController.navigate(Screens.SettingsScreen.name)
                                scope.launch { drawerState.close() }
                            }
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        text = "Help",
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .clickable {
                                navController.navigate(Screens.HelpScreen.name)
                                scope.launch { drawerState.close() }
                            }
                    )
                }
            }
        ) {
            scaffoldBody()
        }
    } else {
        scaffoldBody()
    }
}