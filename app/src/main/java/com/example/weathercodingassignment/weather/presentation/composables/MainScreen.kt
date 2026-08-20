package com.example.codingtestassignment.weather.presentation.composables

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.codingassignment.ui.composables.SearchScreen
import com.example.weathercodingassignment.weather.domain.model.WeatherInfo
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    searchText: String,
    isSearching: Boolean,
    weather: WeatherInfo,
    onSearchTextChange: (String) -> Unit,
    fetchLocation: () -> Unit
) {


    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            fetchLocation()
        } else {
            // out of scope Handle permission denial
        }
    }
    LaunchedEffect(locationPermissionState) {
        if (!locationPermissionState.status.isGranted) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

        } else {
            // out of scope : to show rationale if needed
        }
    }
    SearchScreen(searchText, isSearching, weather, onSearchTextChange)
}