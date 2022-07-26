package com.example.demokkm

import io.ktor.client.*

expect class AppHttpClient() {

    fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient

}