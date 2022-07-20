package com.example.demokkm

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }

    fun getMyAppName(): String {
        return Platform().appName
    }
}