plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    id("com.google.dagger.hilt.android")
//    id("org.jetbrains.kotlin.kapt")
    id("com.google.devtools.ksp")
    id("androidx.room")
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.syncro"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.syncro"
        minSdk = 29
        targetSdk = 35
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

    room {
        schemaDirectory("$projectDir/schemas")
    }
}

//kapt {
//    useBuildCache = true
//    correctErrorTypes = true
//    arguments {
////        arg("dagger.formatGeneratedSource", "disabled")
//    }
//}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    ksp(libs.hilt.android.compiler) // kapt
    // For instrumentation tests
    androidTestImplementation(libs.hilt.android.testing)
//    kaptAndroidTest(libs.hilt.android.compiler)
    // For local unit tests
    testImplementation(libs.hilt.android.testing)
//    kaptTest(libs.hilt.android.compiler)

    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler) // kapt
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.androidx.room.testing)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.retrofit2.converter.kotlinx.serialization)
    implementation(libs.okhttp)

//    implementation(libs.kotlin.metadata.jvm)
}