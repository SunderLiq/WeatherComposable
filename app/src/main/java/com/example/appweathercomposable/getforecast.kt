package com.example.appweathercomposable
import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

internal fun getForecast (city: String, state: MutableState<String>, context: Context){
    val url = "https://api.weatherapi.com/v1/current.json" +
            "?key=$API_KEY&" +
            "q=$city" +
            "&lang=ru"
    val queueRequestForecast = Volley.newRequestQueue(context)
    val stringRequest = StringRequest(
        com.android.volley.Request.Method.GET,
        url,
        {
            response ->
            val obj = JSONObject(response)
            state.value = "\nТемпература: " + obj.getJSONObject("current").getString("temp_c") + " C°" +
                    "\nСкорость ветра: +" + obj.getJSONObject("current").getString("wind_mph") + " м/с " +
                    "\nНаправление ветра: "
            when (obj.getJSONObject("current").getInt("wind_degree")){
                in 337..360 -> state.value += "северный"
                in 0..22  -> state.value += "северный"
                in 23..68 -> state.value += "северо-восточный"
                in 67..112 -> state.value += "восточный"
                in 113..158 -> state.value += "юго-восточный"
                in 159..204 -> state.value += "южный"
                in 205..250 -> state.value += "юго-заподный"
                in 251..296 -> state.value += "заподный"
                in 297..336 -> state.value += "северо-заподный"
            }
        },
        {
            error ->
            Log.d("MyLog", "$error")
        }
    )
    queueRequestForecast.add(stringRequest)
}
