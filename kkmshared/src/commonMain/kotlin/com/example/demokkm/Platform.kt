package com.example.demokkm

import io.ktor.client.*

expect class Platform() {
    val platform: String

    val appName: String

    fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient
}