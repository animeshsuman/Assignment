package com.example.codingtestassignment.weather.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathercodingassignment.utils.Constants
import com.example.weathercodingassignment.utils.Result
import com.example.weathercodingassignment.weather.domain.model.WeatherInfo
import com.example.codingtestassignment.weather.domain.use_cases.GetWeatherInfoUseCase
import com.example.weathercodingassignment.weather.presentation.WeatherInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WeatherInfoViewModel @Inject constructor(private val getWeatherInfoUseCase: GetWeatherInfoUseCase) :
    ViewModel() {

    val searchQuery = MutableStateFlow("")

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _uiState = mutableStateOf(WeatherInfoState())
    val uiState: State<WeatherInfoState> = _uiState

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        observeSearchQuery()
    }

    fun onSearchTextChange(text: String) {
        searchQuery.value = text
    }

    fun onLocationUpdate(lat: String, long: String) {
        getWeatherInfoUseCase.weatherData(lat = lat, long = long)
            .onStart { _isSearching.value = true }
            .onEach { result ->
                _isSearching.value = false
                updateResultToUI(result)
            }.launchIn(viewModelScope)
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun observeSearchQuery() {
        searchQuery
            .debounce(500)
            .distinctUntilChanged()
            .filter { it.length > 2 }
            .onEach { _isSearching.value = true }
            .flatMapLatest { query ->
                getWeatherInfoUseCase.weatherData(query)
            }
            .onEach { result ->
                _isSearching.value = false
                updateResultToUI(result)
            }.launchIn(viewModelScope)
    }

    private suspend fun updateResultToUI(result: Result<WeatherInfo>) {
        when (result) {
            is Result.Error -> {
                _uiState.value = _uiState.value.copy(
                    weatherInfo = WeatherInfo(description = Constants.NO_DATA_AVAILABLE),
                )
                _eventFlow.emit(
                    UIEvent.ShowToast(
                        result.message ?: "Unknown error"
                    )
                )
            }

            is Result.Success ->
                result.data?.let {
                    _uiState.value = _uiState.value.copy(
                        weatherInfo = it,
                    )
                }
        }
    }

    sealed class UIEvent {
        data class ShowToast(val message: String) : UIEvent()
    }
}
