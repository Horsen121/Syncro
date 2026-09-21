plugins {
    id("myproject.android.library")
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.core.current_user"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.androidx.hilt)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.datastore.preferences)
}