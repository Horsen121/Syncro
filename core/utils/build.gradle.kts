plugins {
    id("myproject.android.library")
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.example.core.utils"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.tink.android)
    api(libs.androidx.datastore.preferences)

    implementation(libs.bundles.androidx.hilt)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}