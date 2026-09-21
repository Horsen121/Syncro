pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Syncro"
include(":app")

include(":core:ui")
include(":core:network")
include(":core:database")
include(":core:utils")

include(":shared")
include(":shared:shared_task")

include(":feature:login")
include(":feature:registration")
include(":feature:group_modules:groups")
include(":feature:group_modules:group")
include(":feature:group_modules:add_edit_group")
include(":feature:group_modules:group_chat")
include(":feature:task_modules:add_edit_task")
include(":feature:task_modules:task")
include(":feature:solution_modules:add_edit_solution")
include(":feature:solution_modules:solution")
include(":feature:solution_modules:solutions")
include(":feature:reminders")
include(":feature:peoples")
include(":feature:settings")
include(":core:current_user")
