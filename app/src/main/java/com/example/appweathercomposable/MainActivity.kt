package com.example.appweathercomposable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import com.example.appweathercomposable.screens.BackGround
import com.example.appweathercomposable.screens.ListItem
import com.example.appweathercomposable.screens.MainCard
import com.example.appweathercomposable.screens.TabLayout
import com.example.appweathercomposable.ui.theme.AppWeatherComposableTheme

const val API_KEY = "bbff4996a1fe483486b184236240110"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppWeatherComposableTheme {
                BackGround()
                Column {
                    MainCard()
                    TabLayout()
                }
            }
        }
    }
}
