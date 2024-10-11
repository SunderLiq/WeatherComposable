package com.example.appweathercomposable.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.appweathercomposable.R
import com.example.appweathercomposable.data.WeatherModel
import com.example.appweathercomposable.ui.theme.PurpleGrey40
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@Composable
fun BackGround() {
    Image(
        painterResource(id = R.drawable.clear_day),
        contentDescription = "Clear day",
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.7f),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun MainCard(selectedDay: MutableState<WeatherModel>) {
    Column(
        modifier = Modifier
            .padding(top = 60.dp, start = 10.dp, end = 10.dp),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            shape = RoundedCornerShape(2.dp),
            colors = CardDefaults.cardColors(
                containerColor = PurpleGrey40,
                contentColor = Color.White
            )
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = selectedDay.value.time,
                        style = TextStyle(fontSize = 15.sp)
                    )
                    AsyncImage(
                        model = "https:${selectedDay.value.icon}",
                        contentDescription = "selected forecast icon",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(top = 5.dp)
                    )
                }
                Text(text = selectedDay.value.city, style = TextStyle(fontSize = 30.sp))
                Text(
                    text = selectedDay.value.currentTemp + " °C",
                    style = TextStyle(fontSize = 50.sp),
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = selectedDay.value.condition,
                    style = TextStyle(fontSize = 18.sp),
                    modifier = Modifier.padding(top = 2.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        painterResource(id = R.drawable.search_icon_white),
                        contentDescription = "Search_button",
                        modifier = Modifier
                            .size(30.dp)
                            .padding(top = 5.dp)
                    )
                    Text(text = "${selectedDay.value.minTemp.substringBefore(".")} / ${selectedDay.value.maxTemp.substringBefore(".")}°C")
                    Icon(
                        painterResource(id = R.drawable.refresh_icon_white),
                        contentDescription = "Refresh_button",
                        modifier = Modifier
                            .size(28.dp)
                            .padding(top = 5.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout(daysList: MutableState<List<WeatherModel>>) {
    val tabList = listOf("Почасовой", "По дням")
    val pagerState = com.google.accompanist.pager.rememberPagerState()
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(10.dp)
    ) {
        androidx.compose.material.TabRow(
            selectedTabIndex = tabIndex,
            indicator = { pos ->
                androidx.compose.material.TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, pos)
                )
            },
            backgroundColor = PurpleGrey40,
            contentColor = Color.White
        ) {
            tabList.forEachIndexed { index, text ->
                Tab(
                    selected = false,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(text = text, style = TextStyle(color = Color.White))
                    }
                )
            }
        }
        HorizontalPager(count = tabList.size, state = pagerState, modifier = Modifier.weight(1f)) {
            _ ->
            LazyColumn (Modifier.fillMaxSize()) {
                itemsIndexed(
                    daysList.value
                ){
                    _, item -> ListItem(item)
                }

            }
        }
    }
}