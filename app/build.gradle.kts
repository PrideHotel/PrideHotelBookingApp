// app/build.gradle.kts

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android") // Hilt Android Gradle plugin
    alias(libs.plugins.ksp) // KSP plugin
}

android {
    namespace = "com.pridehotel.booking"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pridehotel.booking"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13" // Updated for Kotlin 2.0.21 compatibility
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Ktor
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    // OpenAI Client
    implementation(libs.openai.client)

    // Hilt
    implementation("com.google.dagger:hilt-android:${libs.versions.hilt.get()}")
    ksp("com.google.dagger:hilt-compiler:${libs.versions.hilt.get()}")

    // AndroidX & Compose - using catalog versions and aliases
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Compose BOM for consistent versions
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)

    // Navigation
    implementation("androidx.navigation:navigation-compose:${libs.versions.nav.get()}")
    implementation("androidx.hilt:hilt-navigation-compose:${libs.versions.hiltNav.get()}")

    // Material Icons Extended
    implementation("androidx.compose.material:material-icons-extended:${libs.versions.icons.get()}")

    // Firebase - relies on BOM for versions
    implementation(platform("com.google.firebase:firebase-bom:${libs.versions.bom.get()}"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-database-ktx")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:${libs.versions.retro.get()}")
    implementation("com.squareup.retrofit2:converter-gson:${libs.versions.retro.get()}")

    // Accompanist Pager - check for migration to official Compose Pager if issues arise
    implementation("com.google.accompanist:accompanist-pager:${libs.versions.pager.get()}")

    // Coil
    implementation("io.coil-kt:coil-compose:${libs.versions.coil.get()}")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}

