plugins {
//    id("com.android.application")
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.gms.google-services")
    id("kotlin-parcelize")
}

android {
    namespace = "uk.ac.tees.mad.d3614099"
    compileSdk = 34

    defaultConfig {
        applicationId = "uk.ac.tees.mad.d3614099"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.material)
    implementation(libs.firebase.database)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //viewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    //Splash Api
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("androidx.compose.material:material-icons-extended:1.6.4")
    implementation("androidx.navigation:navigation-compose:2.7.7")

    //Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth-ktx")
    // firebase storage
    implementation("com.google.firebase:firebase-storage-ktx")

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // coil for asyncImage
    implementation("io.coil-kt:coil-compose:2.6.0")

    //implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation ("androidx.compose.runtime:runtime-livedata:1.6.5")

    // Permissions library to request permissions - by quadri
    implementation ("com.google.accompanist:accompanist-permissions:0.35.0-alpha")

    // Location library to get user's location
    implementation ("com.google.android.gms:play-services-location:21.2.0")

    // Gson library to convert json to object
    implementation ("com.google.code.gson:gson:2.8.9")

}