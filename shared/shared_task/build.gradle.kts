plugins {
    id("myproject.android.feature")
}

android {
    namespace = "com.example.shared_task"
}

dependencies {
    implementation(project(":core:database"))
    implementation(project(":core:network"))
}