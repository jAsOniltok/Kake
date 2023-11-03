import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link
import kotlinx.html.script

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.serialization.plugin)
//    alias(libs.plugins.kobwebx.markdown)
}

group = "com.kake.base"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            description.set("Powered by Kobweb")
        }
    }
}

kotlin {
    // This example is frontend only. However, for a fullstack app, you can uncomment the includeServer parameter
    // and the `jvmMain` source set below.
    configAsKobwebApplication("Kake", includeServer = true)

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(project(":shared"))
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(libs.kobweb.core)
                implementation(libs.kobweb.silk)
                implementation(libs.kobweb.silk.icons.fa)
                implementation(libs.kotlinx.serialization)
                // This default template uses built-in SVG icons, but what's available is limited.
                // Uncomment the following if you want access to a large set of font-awesome icons:
                // implementation(libs.silk.icons.fa)
            }
        }

        // Uncomment the following if you pass `includeServer = true` into the `configAsKobwebApplication` call.
        val jvmMain by getting {
            dependencies {
                implementation(libs.kobweb.api)
                implementation(libs.mongo.database)
                implementation(libs.kotlinx.serialization)
            }
        }
    }
}
