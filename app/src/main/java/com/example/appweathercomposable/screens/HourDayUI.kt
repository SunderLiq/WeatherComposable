package com.example.appweathercomposable.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.appweathercomposable.data.WeatherModel
import com.example.appweathercomposable.ui.theme.PurpleGrey40

@Composable
fun ListItem (item: WeatherModel){
    Card (modifier = Modifier
        .fillMaxWidth()
        .padding(top = 3.dp),
        colors = CardDefaults.cardColors(
            containerColor = PurpleGrey40,
            contentColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Row (modifier = Modifier.padding(12.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            Column (verticalArrangement = Arrangement.SpaceBetween){
                Text(text = item.time, style = TextStyle(fontSize = 18.sp))
            }
            Text(text = item.currentTemp.ifEmpty { "${item.minTemp.substringBefore(".").isPositive()}${item.minTemp.substringBefore(".")} / ${item.minTemp.substringBefore(".").isPositive()}${item.maxTemp.substringBefore(".")}" }, style = TextStyle(fontSize = 25.sp))
            AsyncImage(
                model = "https:${item.icon}",
                contentDescription = "Current weather icon",
                modifier = Modifier
                    .size(50.dp)

            )
        }
    }
}

fun String.isPositive(): String {
    return if (this.toInt() > 0) "+"
    else ""
}