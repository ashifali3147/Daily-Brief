import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger)
    kotlin("kapt")
}

val localProperties = Properties()
val localPropertiesFile = rootProject.file("local.properties")

if (localPropertiesFile.exists()) {
    localPropertiesFile.inputStream().use {
        localProperties.load(it)
    }
}

val newsApiKey =
    localProperties.getProperty("NEWS_API_KEY")
        ?: error("NEWS_API_KEY not found in local.properties")

val newsApiBaseUrl =
    localProperties.getProperty("NEWS_API_BASE_URL")
        ?: error("NEWS_API_BASE_URL not found in local.properties")

android {
    namespace = "com.tlw.dailybrief"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.tlw.dailybrief"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String", "NEWS_API_KEY", "\"$newsApiKey\""
        )
        buildConfigField(
            "String", "NEWS_API_BASE_URL", "\"$newsApiBaseUrl\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    implementation(libs.work.manager)
    implementation(libs.work.manager.dagger)
    kapt(libs.work.manager.dagger.kapt)

    implementation(libs.dagger.hilt)
    kapt(libs.dagger.kapt)

    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.convertor)

    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
}