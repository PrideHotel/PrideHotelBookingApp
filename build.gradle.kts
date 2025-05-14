// Top‐level build.gradle.kts

plugins {
    // Android
    alias(libs.plugins.android.application) apply false
    // Kotlin + Compose compiler
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    // Hilt - Use alias from version catalog
    alias(libs.plugins.dagger.hilt.android) apply false
    // Firebase / Google-Services
    id("com.google.gms.google-services") version "4.4.2" apply false // Keep this if not in catalog or manage via catalog
    // KSP Plugin
    alias(libs.plugins.ksp) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    // Dependencies block in buildscript is generally for older Gradle versions or specific needs.
    // With version catalogs and plugin aliases, direct classpath dependencies here are often not needed.
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

