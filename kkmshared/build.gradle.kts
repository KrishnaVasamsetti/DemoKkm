plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.4.31"
    id("com.squareup.sqldelight")

}

kotlin {
    android()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "kkmshared"
        }
    }

    sourceSets {
        val ktorVersion = "2.0.3"
        val sqlDelightVersion = "1.5.3"

        val commonMain by getting {
            dependencies {
                implementation(kotlin("test"))

                //Network call
                implementation("io.ktor:ktor-client-core:$ktorVersion")

                //Logging
                implementation("io.ktor:ktor-client-logging:$ktorVersion")

                //Json Parsing
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

                //SQLDelight
                implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")

            }
        }
//        val commonTest by getting
        val androidMain by getting {
            dependencies {

                implementation("androidx.annotation:annotation:1.4.0")

                //Network call
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
                implementation("io.ktor:ktor-client-android:$ktorVersion")

                //SQLDelight
                implementation ("com.squareup.sqldelight:runtime-jvm:$sqlDelightVersion")
                implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
            }
        }
//        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                //Network call
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")

                //SQLDelight
                implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
            }
        }
//        val iosX64Test by getting
//        val iosArm64Test by getting
//        val iosSimulatorArm64Test by getting
//        val iosTest by creating {
//            dependsOn(commonTest)
//            iosX64Test.dependsOn(this)
//            iosArm64Test.dependsOn(this)
//            iosSimulatorArm64Test.dependsOn(this)
//        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 23
        targetSdk = 32
    }
}

sqldelight {
    //Name of your database, might be anything you want
    database("AppDatabase") {
        packageName = "com.example.demokkm.database"
    }
}