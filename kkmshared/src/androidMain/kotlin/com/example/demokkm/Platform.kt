package com.example.demokkm

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
    actual val appName: String
        get() = "MyAndroidKmmApp"
}