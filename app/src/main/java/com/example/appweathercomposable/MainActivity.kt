package com.example.appweathercomposable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.appweathercomposable.screens.MainScreen
import com.example.appweathercomposable.ui.theme.AppWeatherComposableTheme

const val API_KEY = "bbff4996a1fe483486b184236240110"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppWeatherComposableTheme {
                MainScreen()
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    ScreenCurrentWeather("Москва", this)
//                }
            }
        }
    }
}
