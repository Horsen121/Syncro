plugins {
    id("myproject.android.library")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.core.network"
}

dependencies {
    implementation(project(":core:utils"))

    implementation(libs.bundles.androidx.retrofit)
}