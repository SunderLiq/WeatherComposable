package com.example.appweathercomposable

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appweathercomposable.screens.BackGround
import com.example.appweathercomposable.screens.MainCard
import com.example.appweathercomposable.screens.TabLayout
import com.example.appweathercomposable.ui.theme.AppWeatherComposableTheme

const val API_KEY = "bbff4996a1fe483486b184236240110"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            getData("Donetsk", this)
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

private fun getData (city: String, context: Context){
    val url = "https://api.weatherapi.com/v1/forecast.json?key=" +
            API_KEY +
            "&q=" +
            city +
            "&days=11"
    val queue = Volley.newRequestQueue(context)
    val sRequest = StringRequest(
        com.android.volley.Request.Method.GET,
        url,
        {
            response ->
            Log.d("MyLog", "Volley response $response")

        },
        {
            Log.d("MyLog", "Volley error $it")
        }
    )
    queue.add(sRequest)
}