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
import com.example.demokkm.database.DatabaseDriverFactory
import com.example.demokkm.database.KDatabase
import com.example.demokkm.preferences.KMMPreference
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


fun greet(): String {
    return Greeting().greeting()
}
fun getAppName() = Greeting().getMyAppName()
fun getLocalDateTime() = Greeting().getCurrentDataTime()


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

        val appDb = KDatabase(DatabaseDriverFactory(applicationContext))
        val pref = KMMPreference(application)
        pref.putInt("Visits", pref.getInt("Visits", 0)+1)

        mainScope.launch {
            kotlin.runCatching {
                val model = userDetails.fetchStudentListAsModel()
                appDb.insertStudentItem(model)
            }.onSuccess {
                val visits = pref.getInt("Visits", 0)
                val list = appDb.getAllStudentList()
                tv.text = "visits: $visits Success: $list Time: ${getLocalDateTime()}"
                Log.d("TAG", "onCreate: $it")
            }.onFailure {
                tv.text = it.message
                Log.d("TAG", "onCreate: ${it.message}")
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

