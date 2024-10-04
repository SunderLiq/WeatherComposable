package com.example.appweathercomposable.screens

import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.appweathercomposable.R
import com.example.appweathercomposable.ui.theme.Purple40
import com.example.appweathercomposable.ui.theme.White80

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    Image(
        painterResource(id = R.drawable.clear_day),
        contentDescription = "Clear day",
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.7f),
        contentScale = ContentScale.Crop
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp, start = 10.dp, end = 10.dp),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            shape = RoundedCornerShape(2.dp),
            colors = CardDefaults.cardColors(
                containerColor = Purple40,
                contentColor = Color.White
            )
                )
        {
            Column (modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally){
                Row (modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween){
                    Text(text = "03.10.2024 23:30",
                    style = TextStyle(fontSize = 15.sp))
                    AsyncImage(model = "https://cdn.weatherapi.com/weather/64x64/night/113.png", contentDescription = "night_icon",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(top = 5.dp))
                }
                Text(text = "Донецк", style = TextStyle(fontSize = 30.sp))
                Text(text = "36.4°", style = TextStyle(fontSize = 50.sp), modifier = Modifier.padding(top = 10.dp))
                Text(text = "Солнечно", style = TextStyle(fontSize = 18.sp), modifier = Modifier.padding(top = 2.dp))
                Row (modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween){
                    Icon(painterResource(id = R.drawable.search_icon_white), contentDescription = "Search_button", modifier = Modifier
                        .size(30.dp)
                        .padding(top = 5.dp))
                    Text(text = "25.5° / 37.2°")
                    Icon(painterResource(id = R.drawable.refresh_icon_white), contentDescription = "Search_button", modifier = Modifier
                        .size(30.dp)
                        .padding(top = 5.dp))
                }
            }
        }
    }
}