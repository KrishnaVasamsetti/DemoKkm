package com.example.demokkm

expect class AppLogger() {

    fun d(tag: String = "AppLogger", message: String)
}