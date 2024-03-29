import com.varabyte.kobweb.gradle.library.util.configAsKobwebLibrary

plugins {
    id("com.android.library")
//    id("org.jetbrains.kotlin.android") // Kotlin Android 플러그인
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.library)
    alias(libs.plugins.serialization.plugin)
    alias(libs.plugins.mongodb.realm)
    alias(libs.plugins.moko.kswift.plugin)
    alias(libs.plugins.google.devtools)
    alias(libs.plugins.kmp.coroutine)
}

group = "com.kake.base"
version = "1.0-SNAPSHOT"

android {
    namespace = "com.kake.base"
    compileSdk = 34
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

}

kswift {
    install(dev.icerock.moko.kswift.plugin.feature.PlatformExtensionFunctionsFeature)
}

kotlin {
    configAsKobwebLibrary(includeServer = true)
    android()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }
    @Suppress("UNUSED_VARIABLE") // Suppress spurious warnings about sourceset variables not being used
    sourceSets {
        all {
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(libs.kotlinx.serialization)
                implementation(libs.kotlinx.coroutines)
                api(libs.moko.mvvm.core)
                api(libs.moko.mvvm.flow)
                api(libs.kmm.viewmodel.core)
            }
        }

        val jsMain by getting {
            dependencies {
                /*   implementation(compose.html.core)
                implementation(libs.kobweb.core)
                implementation(libs.kobweb.silk.core)
                implementation(libs.kobweb.silk.icons.fa)*/
            }
        }
        val jvmMain by getting {
            dependencies {
                /*     implementation(libs.kobweb.api)
                implementation(libs.kotlinx.serialization)*/
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.mongodb.sync)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation(libs.mongodb.sync)
                implementation(libs.kotlinx.coroutines)
                implementation(libs.moko.kswift.lib)
                implementation(libs.mvvm.flow.swiftui)
                api(libs.moko.mvvm.core)
                api(libs.moko.mvvm.flow)

            }
        }
    }
}