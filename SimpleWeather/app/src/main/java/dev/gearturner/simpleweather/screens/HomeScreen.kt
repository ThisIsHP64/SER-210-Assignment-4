package dev.gearturner.simpleweather.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FinalOut(townName: String, currentTemp: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.LightGray)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = townName,
                    fontSize = 24.sp
                )

                Spacer(
                    modifier = Modifier
                        .width(200.dp)
                )

                Text(
                    text = currentTemp,
                    fontSize = 24.sp
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FinalPreview() {
    FinalOut("Hamden", "40*C")
}