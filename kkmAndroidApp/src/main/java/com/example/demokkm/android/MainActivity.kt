package com.example.demokkm.android

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.demokkm.Greeting
import com.example.demokkm.UserDetails
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


fun greet(): String {
    return Greeting().greeting()
}
fun getAppName() = Greeting().getMyAppName()


class MainActivity : AppCompatActivity() {

    private val mainScope = MainScope()
    private val userDetails = UserDetails()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)

        val isValid1 = UserDetails().isValidDetails("Test", "Pass")
        val isValid2 = UserDetails().isValidDetails("", "")

        tv.text = "Loading..."
//        tv.text = getAppName()+isValid1+isValid2+isAutomaticDateTime()

        mainScope.launch {
            kotlin.runCatching {
                userDetails.fetchStudentList()
            }.onSuccess {
                tv.text = it
            }.onFailure {
                tv.text = it.message
            }
        }

    }

    private fun isAutomaticDateTime(): String {

        val timeSettings = Settings.Global.getString(
                    this.contentResolver,
            Settings.Global.AUTO_TIME)

        val timeZoneSettings = Settings.Global.getInt(contentResolver, Settings.Global.AUTO_TIME_ZONE)

        val intent = Intent(Settings.ACTION_DATE_SETTINGS)
//        intent.data = Uri.parse("package:$packageName")
        startActivity(intent)
        Log.d("TAG", "isAutomaticDateTime: $timeSettings")
        return "time: $timeSettings zone:$timeZoneSettings"
    }
}

