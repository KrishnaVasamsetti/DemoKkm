package com.example.demokkm

actual class AppLogger actual constructor() {

    actual fun d(tag: String, message: String) {
        println("$tag: $message")
    }
}