package com.example.codingassignment.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.weathercodingassignment.utils.TestTag.PROGRESS_BAR_TAG
import com.example.weathercodingassignment.utils.TestTag.SEARCH_TAG
import com.example.weathercodingassignment.utils.TestTag.WEATHER_CARD_TAG
import com.example.weathercodingassignment.weather.domain.model.WeatherInfo

@Composable
fun SearchScreen(
    searchText: String,
    isSearching: Boolean,
    weather: WeatherInfo,
    onSearchTextChange: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 30.dp, horizontal = 15.dp)
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            label = {
                Text("Weather")
            },
            placeholder = {
                Text("Enter City,State or Zip Code")
            },
            modifier = Modifier.testTag(SEARCH_TAG).fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (isSearching) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag(PROGRESS_BAR_TAG)
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        } else {
            Card(modifier = Modifier.testTag(WEATHER_CARD_TAG))
            {
                WeatherScreen(weatherData = weather)
            }
        }
    }
}

