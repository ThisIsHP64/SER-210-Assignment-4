package dev.gearturner.simpleweather.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import dev.gearturner.simpleweather.model.Town
import dev.gearturner.simpleweather.model.getTowns

@Composable
fun TownList(navController: NavController) {
    TownListColumn(
        TownList = getTowns(),
        navController = navController
    )
}

@Composable
fun TownListColumn(TownList: List<Town>, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize())
    {
        items(TownList) { town ->
            TownItem(town = town, onClick = {
                navController.navigate((Screen.Details.createRoute(town.name)))
            })
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    val towns = getTowns()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        items(towns) { town ->
            TownItem(town = town, onClick = {
                navController.navigate(Screen.Details.createRoute(town.name))
            })
        }
    }
}

@Composable
fun TownItem(town: Town, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.LightGray)
            .clickable{onClick()}

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
                Text(
                    text = town.name,
                    fontSize = 24.sp
                )
        }
    }
}