package com.example.demokkm

import kotlinx.datetime.Clock

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }

    fun getMyAppName(): String {
        return Platform().appName
    }

    fun getCurrentDataTime(): String {
        val currentMoment = Clock.System.now()
        return currentMoment.toString()
//        return currentMoment.toLocalDateTime(TimeZone.currentSystemDefault()).toString()
    }
}