package com.example.appweathercomposable

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScreenCurrentWeather(currentCity: String, context: Context) {
    val forecastRememberData = remember {
        mutableStateOf("")
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxHeight(0.5f)
            .fillMaxSize()
            ,contentAlignment = Alignment.Center) {
            Text(
                text = "В городе $currentCity на данный момент ${forecastRememberData.value}",
            )
        }
        Box(modifier = Modifier
            .fillMaxHeight()
            .fillMaxSize()
            ,contentAlignment = Alignment.BottomCenter) {
            ElevatedButton(
                modifier = Modifier
                    .padding(15.dp),
                onClick = {
                    getForecast(city = currentCity, state = forecastRememberData, context = context)
                }) {
                Icon(
                    Icons.Filled.Refresh,
                    contentDescription = "Refresh icon")
                Text(modifier = Modifier.padding(start = 5.dp), text = "Обновить")
            }
        }
    }
}