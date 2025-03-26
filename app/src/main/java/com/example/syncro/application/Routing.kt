package com.example.syncro.application

sealed class Routing(val route: String) {
    data object LoginScreen: Routing("login_screen")
    data object RegistrationScreen: Routing("registration_screen")

    data object GroupsScreen: Routing("groups_screen")
    data object SolutionsScreen: Routing("solutions_screen")
    data object PeoplesScreen: Routing("peoples_screen")
    data object RemindersScreen: Routing("reminders_screen")

    data object GroupScreen: Routing("group_screen")
    data object TaskScreen: Routing("task_screen")
    data object SolutionScreen: Routing("solution_screen")
    data object AddEditGroupScreen: Routing("add_edit_group_screen")

    data object GroupChatScreen: Routing("group_chat_screen")
    data object SettingsScreen: Routing("settings_screen")
}