package com.example.demokkm

import android.util.Log
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import java.util.concurrent.TimeUnit

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    actual val appName: String
        get() = "MyAndroidKmmApp"

    actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(OkHttp) {
        config(this)

        install(ContentNegotiation) {
            json()
        }

        install(Logging) {

            logger = object : Logger {
                override fun log(message: String) {
                    Log.i("API", message)
                }
            }
            level = LogLevel.BODY
        }

        engine {
            config {
                retryOnConnectionFailure(false)
                connectTimeout(30, TimeUnit.SECONDS)
            }
        }
    }
}