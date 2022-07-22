package com.example.demokkm

import io.ktor.client.*
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import platform.UIKit.UIDevice

actual class Platform actual constructor() {
    actual val platform: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    actual val appName: String
        get() = "My SwiftUI KMM App"

    actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Darwin) {
        config(this)

        install(ContentNegotiation) {
            json()
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println("API: $message")
                }
            }
            level = LogLevel.BODY
        }

        engine {
            configureRequest {
                setAllowsCellularAccess(true)
            }
        }
    }

}