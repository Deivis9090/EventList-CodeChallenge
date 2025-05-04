plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.listevents"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.listevents"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.room.common.jvm)
    implementation(libs.sqlite.android)
    implementation(libs.room.runtime)
    implementation(libs.appcompat.v161)
    implementation(libs.material.v190)
    annotationProcessor(libs.room.compiler)
    testImplementation(libs.junit)
    implementation(libs.glide)
    annotationProcessor(libs.glide)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}