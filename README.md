# WeatherCodingAssignment

A modern Android application that provides real-time weather updates using the **OpenWeatherMap API**. This project serves as a demonstration of current Android development best practices, featuring a fully reactive UI and a robust offline-first architecture.

## 🚀 Features
*   **Real-time Weather:** Fetches current temperature, conditions, and icons based on search queries.
*   **Location-Based Updates:** Automatically detects the user's current coordinates using **Google Play Services Location** to provide localized weather data.
*   **Offline Support:** Implements local caching via **Room Database**, ensuring weather data is available even without an internet connection.
*   **Image Integration:** Dynamically loads weather icons using **Coil 3**.
*   **Unit Conversion:** Automatically handles Kelvin to Celsius conversions using project constants.

## 🛠 Technical Stack
*   **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material Design 3)
*   **Architecture:** MVVM (Model-View-ViewModel)
*   **Dependency Injection:** [Hilt (Dagger)](https://developer.android.com/training/dependency-injection/hilt-android)
*   **Networking:** [Retrofit 3](https://square.github.io/retrofit/) with [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization)
*   **Database:** [Room](https://developer.android.com/training/data-storage/room)
*   **Asynchronous Tasks:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html)
*   **Image Loading:** [Coil 3](https://coil-kt.github.io/coil/)
*   **Permissions:** [Accompanist Permissions](https://google.github.io/accompanist/permissions/)

## ⚙️ Setup Instructions

1.  **Clone the repository:**.      git clone https://github.com/your-username/WeatherCodingAssignment.git
    
2.  **API Key Configuration**  Open app/src/main/java/com/example/weathercodingassignment/utils/Constants.kt and add your OpenWeatherMap API Key:
  const val API_KEY = "YOUR_API_KEY_HERE"

## Testing
UI Tests: Built using androidx.test rules to verify Compose components.
    
    
