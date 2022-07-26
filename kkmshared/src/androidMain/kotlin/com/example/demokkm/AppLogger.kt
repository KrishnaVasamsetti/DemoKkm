package com.example.demokkm

import android.util.Log

actual class AppLogger actual constructor() {

    actual fun d(tag: String, message: String) {
        Log.d(tag, message)
    }
}