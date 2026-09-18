plugins {
    id("myproject.android.application")
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.syncro"

    defaultConfig {
        applicationId = "com.example.syncro"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":core:utils"))

    implementation(project(":shared:shared_task"))

    implementation(project(":feature:login"))
    implementation(project(":feature:registration"))
    implementation(project(":feature:peoples"))
    implementation(project(":feature:reminders"))
    implementation(project(":feature:settings"))
    implementation(project(":feature:group_modules:add_edit_group"))
    implementation(project(":feature:group_modules:group"))
    implementation(project(":feature:group_modules:group_chat"))
    implementation(project(":feature:group_modules:groups"))
    implementation(project(":feature:task_modules:add_edit_task"))
    implementation(project(":feature:task_modules:task"))
    implementation(project(":feature:solution_modules:add_edit_solution"))
    implementation(project(":feature:solution_modules:solution"))
    implementation(project(":feature:solution_modules:solutions"))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.activity.navigation)

    implementation(libs.bundles.androidx.hilt)
}