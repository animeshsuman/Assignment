package com.example.codingassignment.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.error
import coil3.request.fallback
import coil3.request.placeholder
import com.example.weathercodingassignment.utils.TestTag.CITY_NAME_TAG
import com.example.weathercodingassignment.weather.domain.model.WeatherInfo

@Composable
fun WeatherScreen(weatherData: WeatherInfo) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        val imageUrl = weatherData.imageUrl
        val placeholder = android.R.drawable.ic_menu_close_clear_cancel
        val imageRequest = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .memoryCacheKey(imageUrl)
            .diskCacheKey(imageUrl)
            .placeholder(placeholder)
            .error(placeholder)
            .fallback(placeholder)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .build()
        AsyncImage(
            model = imageRequest,
            contentDescription = null,
            modifier = Modifier.wrapContentSize()
        )
        // temperature
        Text(
            text = weatherData.temp,
            style = TextStyle(
                fontSize = 24.sp,
            ),
            color = Color.Gray,
        )
        // City Name and Description
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = weatherData.name,
                style = TextStyle(
                    fontSize = 24.sp,
                ),
                color = Color.Gray,
                modifier = Modifier.testTag(CITY_NAME_TAG)
            )
            Text(
                text = weatherData.description,
                color = Color.Gray.copy(alpha = 0.7f)
            )
        }
    }
}
