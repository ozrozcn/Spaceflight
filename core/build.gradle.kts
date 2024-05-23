plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.ozrozcn.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug{
            buildConfigField( "String", "BASE_URL", "\"https://api.spaceflightnewsapi.net/v4/\"")
        }

        release {
            buildConfigField( "String", "BASE_URL", "\"https://api.spaceflightnewsapi.net/v4/\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
        buildConfig = true
    }

    dataBinding{
        enable = true
    }
}

dependencies {
    implementation(project(":database"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.hilt.android)
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    kapt(libs.hilt.android.compiler)
    implementation (libs.glide)
    implementation(libs.kotlinx.serialization.json)

    // network
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation (libs.converter.gson)

    implementation(libs.kotlinx.datetime)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}