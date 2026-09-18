plugins {
    id("myproject.android.library")
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

android {
    namespace = "com.example.core.database"
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    implementation(project(":core:utils"))

    implementation(libs.bundles.androidx.room)
    ksp(libs.androidx.room.compiler)
}