package com.example.appweathercomposable

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.appweathercomposable.data.WeatherModel
import com.example.appweathercomposable.screens.BackGround
import com.example.appweathercomposable.screens.MainCard
import com.example.appweathercomposable.screens.TabLayout
import com.example.appweathercomposable.ui.theme.AppWeatherComposableTheme
import org.json.JSONObject

const val API_KEY = "bbff4996a1fe483486b184236240110"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val daysList = remember {
                mutableStateOf(listOf<WeatherModel>())
            }
            val selectedDay = remember {
                mutableStateOf(WeatherModel(
                    "", "", "1.0", "", "", "1.0", "1.0", ""
                ))
            }
            getData("Murmansk", this, daysList, selectedDay)
            AppWeatherComposableTheme {
                BackGround()
                Column {
                    MainCard(selectedDay)
                    TabLayout(daysList)
                }
            }
        }
    }
}

private fun getData (city: String, context: Context,
                     daysList: MutableState<List<WeatherModel>>, selectedDay: MutableState<WeatherModel>){
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
            val list = getForecastByDays(response)
            selectedDay.value = list[0]
            daysList.value = list
        },
        {
            Log.d("MyLog", "Volley error $it")
        }
    )
    queue.add(sRequest)
}

private fun getForecastByDays (response: String): List<WeatherModel> {
    if (response.isEmpty()) return listOf()
    val list = ArrayList<WeatherModel>()
    val mainObject = JSONObject(response)
    val city = mainObject.getJSONObject("location").getString("name")
    val days = mainObject.getJSONObject("forecast").getJSONArray("forecastday")
    for (i in 0 until days.length()) {
        val item = days[i] as JSONObject
        list.add(
            WeatherModel(
                city,
                item.getString("date"),
                "",
                item.getJSONObject("day").getJSONObject("condition").getString("text"),
                item.getJSONObject("day").getJSONObject("condition").getString("icon"),
                item.getJSONObject("day").getString("maxtemp_c"),
                item.getJSONObject("day").getString("mintemp_c"),
                item.getJSONArray("hour").toString()

            )
        )
    }
    list[0] = list[0].copy(
        time = mainObject.getJSONObject("current").getString("last_updated"),
        currentTemp = mainObject.getJSONObject("current").getString("temp_c")
    )
    return list
}