plugins {
    id("myproject.android.library")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.core.ui"
    buildFeatures {
        compose = true
    }
}

dependencies {
    api(platform(libs.androidx.compose.bom))
    api(libs.bundles.compose)
    debugImplementation(libs.androidx.compose.ui.tooling)
}