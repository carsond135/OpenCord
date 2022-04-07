plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("plugin.serialization")
}

val composeVersion = "1.2.0-alpha07"

android {
    compileSdk = 31

    flavorDimensions.add("api")

    defaultConfig {
        applicationId = "com.xinto.opencord"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("int", "DISCORD_VERSION_CODE", "89108")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    productFlavors {
        create("discord") {
            dimension = "api"

            isDefault = true

            buildConfigField("String", "URL_API", "\"https://discord.com/api/v9\"")
            buildConfigField("String", "URL_CDN", "\"https://cdn.discordapp.com\"")
            buildConfigField("String", "URL_GATEWAY", "\"wss://gateway.discord.gg/?v=9&encoding=json\"")
        }

        create("fosscord") {
            dimension = "api"

            applicationIdSuffix = ".fosscord"
            versionNameSuffix = "-fosscord"

            buildConfigField("String", "URL_API", "\"https://api.fosscord.com/v9\"")
            buildConfigField("String", "URL_CDN", "\"https://cdn.fosscord.com\"")
            buildConfigField("String", "URL_GATEWAY", "\"wss://gateway.fosscord.com/?v=9&encoding=json\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = freeCompilerArgs +
                "-Xopt-in=kotlin.RequiresOptIn"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")
    implementation("androidx.preference:preference-ktx:1.2.0")

    implementation("androidx.compose.compiler:compiler:$composeVersion")
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    implementation("androidx.compose.material:material-icons-core:$composeVersion")
    implementation("androidx.compose.material:material-icons-extended:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("androidx.compose.ui:ui-util:$composeVersion")
    implementation("androidx.compose.ui:ui:$composeVersion")

    implementation("io.coil-kt:coil-compose:1.4.0")

    val accompanistVersion = "0.24.5-alpha"
    implementation("com.google.accompanist:accompanist-navigation-animation:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanistVersion")

    implementation("com.google.android.material:material:1.5.0")

    implementation("com.github.hcaptcha:hcaptcha-android-sdk:1.1.0")

    val exoplayerVersion = "2.16.0"
    implementation("com.google.android.exoplayer:exoplayer-core:$exoplayerVersion")
    implementation("com.google.android.exoplayer:exoplayer-dash:$exoplayerVersion")
    implementation("com.google.android.exoplayer:exoplayer-ui:$exoplayerVersion")

    val ktorVersion = "2.0.0-beta-1"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-websockets:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

    implementation("com.github.X1nto:OverlappingPanelsCompose:1.2.0")

    implementation("io.insert-koin:koin-androidx-compose:3.1.5")
}