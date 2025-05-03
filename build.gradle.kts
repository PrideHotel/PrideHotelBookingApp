// Top‐level build.gradle.kts

plugins {
    // Android
    id("com.android.application")        version "8.9.2" apply false
    // Kotlin + Compose compiler
    id("org.jetbrains.kotlin.android")   version "2.0.21" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"   apply false
    // Hilt
    id("com.google.dagger.hilt.android") version "2.48"   apply false
    // Firebase / Google-Services
    id("com.google.gms.google-services") version "4.4.2"  apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // needed on the classpath for legacy pluginResolution
        classpath("com.android.tools.build:gradle:8.9.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.21")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
        classpath("com.google.gms:google-services:4.4.2")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
