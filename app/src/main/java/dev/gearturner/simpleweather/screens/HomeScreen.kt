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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import dev.gearturner.simpleweather.model.Town
import dev.gearturner.simpleweather.model.getTowns
import dev.gearturner.simpleweather.navigation.Screens

@Composable
fun HomeScreen(navController: NavController) {
    val towns = getTowns()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        items(towns) {
            TownItem(town = it) { clickedTownName ->
                navController.navigate(route = Screens.DetailScreen.name + "/$clickedTownName")
            }
        }
    }
}

@Composable
fun TownItem(town: Town, modifier: Modifier = Modifier, itemClick: (String) -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.LightGray)
            .clickable{
                itemClick(town.name)
            }

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