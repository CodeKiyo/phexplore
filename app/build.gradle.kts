plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.mobdeve.phexplore"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mobdeve.phexplore"
        minSdk = 24
        targetSdk = 34
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding.enable = true
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    val fragment_version = "1.6.2"
    implementation ("com.facebook.android:facebook-android-sdk:latest.release")
    implementation ("com.facebook.android:facebook-share:latest.release")


    implementation("androidx.fragment:fragment-ktx:$fragment_version")

    // Firebase Realtime Database
    implementation ("com.google.firebase:firebase-database-ktx:20.0.4")
    implementation ("com.firebaseui:firebase-ui-database:8.0.2")

    // Cloud Firestore
    implementation ("com.google.firebase:firebase-firestore-ktx:24.1.1")
    implementation ("com.firebaseui:firebase-ui-firestore:8.0.2")

    // Firebase Auth
    implementation ("com.google.firebase:firebase-auth-ktx:21.0.3")
    implementation ("com.firebaseui:firebase-ui-auth:8.0.2")

    // Cloud Storage
    implementation ("com.google.firebase:firebase-storage-ktx:20.0.1")
    implementation ("com.firebaseui:firebase-ui-storage:8.0.2")

    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("com.google.firebase:firebase-analytics")

    implementation("com.squareup.picasso:picasso:2.71828")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.google.android.gms:play-services-maps:18.2.0")

}
