plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace       = "com.pridehotel.booking"
    compileSdk      = 35

    defaultConfig {
        applicationId           = "com.pridehotel.booking"
        minSdk                  = 21
        targetSdk               = 35
        versionCode             = 1
        versionName             = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }

    buildFeatures { compose = true }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
}

dependencies {
    // Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt         ("com.google.dagger:hilt-compiler:2.48")

    // Core Compose
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.compose.ui:ui:1.5.4")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.4")
    implementation("androidx.compose.material3:material3:1.1.2")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.4")

    // ← Add these:
    // Jetpack Navigation for Compose
    implementation("androidx.navigation:navigation-compose:2.6.0")
    // Hilt integration with Navigation-Compose
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    // Material Icons (extended)
    implementation("androidx.compose.material:material-icons-extended:1.5.4")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-database-ktx")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Other
    implementation("com.google.accompanist:accompanist-pager:0.28.0")
    implementation("io.coil-kt:coil-compose:2.0.0")

    testImplementation           ("junit:junit:4.13.2")
    androidTestImplementation    ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation    ("androidx.test.espresso:espresso-core:3.5.1")
}

kapt {
    correctErrorTypes = true
}
