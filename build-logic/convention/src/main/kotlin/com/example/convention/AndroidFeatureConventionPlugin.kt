package com.example.convention

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("myproject.android.library")
                apply("org.jetbrains.kotlin.plugin.compose")
                apply("com.google.devtools.ksp")
                apply("com.google.dagger.hilt.android")
            }

            extensions.configure<LibraryExtension> {
                buildFeatures {
                    compose = true
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("implementation", project(":core:current_user"))
                add("implementation", project(":core:ui"))
                add("implementation", project(":core:utils"))
                add("implementation", project(":core:database"))
                add("implementation", project(":core:network"))
                add("implementation", libs.findBundle("androidx.hilt").get())
                add("ksp", libs.findLibrary("hilt.android.compiler").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
            }
        }
    }
}