package com.example.weathercodingassignment

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.app.ActivityCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.codingtestassignment.weather.presentation.WeatherInfoViewModel
import com.example.codingtestassignment.weather.presentation.composables.MainScreen
import com.example.weathercodingassignment.ui.theme.WeatherCodingAssignmentTheme
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherCodingAssignmentTheme {
                val state = viewModel.uiState.value
                val searchText by viewModel.searchQuery.collectAsStateWithLifecycle()
                val isSearching by viewModel.isSearching.collectAsState()
                
                LaunchedEffect(key1 = true) {
                    viewModel.eventFlow.collectLatest { event ->
                        when (event) {
                            is WeatherInfoViewModel.UIEvent.ShowToast -> {
                                showToast(
                                    message = event.message
                                )
                            }
                        }
                    }
                }

                MainScreen(
                    weather = state.weatherInfo,
                    searchText = searchText,
                    isSearching = isSearching,
                    onSearchTextChange = viewModel::onSearchTextChange,
                    fetchLocation = { getLocation() }
                )
            }
        }
    }

    private fun getLocation() {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { loc ->
                if (loc != null) {
                    viewModel.onLocationUpdate(
                        lat = loc.latitude.toString(),
                        long = loc.longitude.toString()
                    )
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
