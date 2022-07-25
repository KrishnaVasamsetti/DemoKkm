package com.example.demokkm.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

//actual class DatabaseDriverFactory {
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
//        return NativeSqliteDriver(dbSchema, "app.db")
//    }
//}