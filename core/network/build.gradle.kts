plugins {
    id("myproject.android.library")
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.core.network"
}

dependencies {
    implementation(project(":core:utils"))

    implementation(libs.bundles.androidx.retrofit)

    implementation(libs.bundles.androidx.hilt)
    ksp(libs.hilt.android.compiler)
}