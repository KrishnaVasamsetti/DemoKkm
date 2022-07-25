package com.example.demokkm.database

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

//actual class DatabaseDriverFactory(private val context: Context) {
//    val dbSchema = object : SqlDriver.Schema {
//        override val version: Int
//            get() = 1_0_0
//
//        override fun create(driver: SqlDriver) {
////            wrapConnection(driver)
//        }
//
//        override fun migrate(driver: SqlDriver, oldVersion: Int, newVersion: Int) {
//
//        }
//
//    }
//    actual fun createDriver(): SqlDriver {
//        return AndroidSqliteDriver(dbSchema, context, "app.db")
//    }
//}