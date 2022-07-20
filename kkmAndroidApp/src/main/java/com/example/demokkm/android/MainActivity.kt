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


fun greet(): String {
    return Greeting().greeting()
}
fun getAppName() = Greeting().getMyAppName()


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)

        val isValid1 = UserDetails().isValidDetails("Test", "Pass")
        val isValid2 = UserDetails().isValidDetails("", "")

        tv.text = getAppName()+isValid1+isValid2+isAutomaticDateTime()

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

