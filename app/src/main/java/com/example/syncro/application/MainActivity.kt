package com.example.syncro.application

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.syncro.application.ui.theme.SyncroTheme
import com.example.syncro.presentation.ui.screens.GroupsScreen
import com.example.syncro.presentation.ui.screens.SolutionScreen
import com.example.syncro.presentation.ui.screens.TaskScreen
import com.example.syncro.presentation.ui.screens.group.AddEditGroupScreen
import com.example.syncro.presentation.ui.screens.group.GroupChatScreen
import com.example.syncro.presentation.ui.screens.group.GroupScreen
import com.example.syncro.presentation.ui.screens.logreg.LoginScreen
import com.example.syncro.presentation.ui.screens.logreg.RegistrationScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SyncroTheme {
                var currentScreen by remember { mutableStateOf(Routing.LoginScreen.route) } // TODO: change to condition

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routing.LoginScreen.route // TODO: change to condition
                ) {
                    composable(route = Routing.LoginScreen.route) {
                        currentScreen = Routing.LoginScreen.route
                        LoginScreen(
                            navController = navController
                        )
                    }
                    composable(route = Routing.RegistrationScreen.route) {
                        currentScreen = Routing.RegistrationScreen.route
                        RegistrationScreen(
                            navController = navController
                        )
                    }
                    composable(route = Routing.GroupsScreen.route) {
                        currentScreen = Routing.GroupsScreen.route
                        GroupsScreen(
                            navController = navController
                        )
                    }
                    composable(
                        route = Routing.GroupScreen.route + "?groupId={groupId}",
                        arguments = listOf(
                            navArgument(name = "groupId") {
                                type = NavType.LongType
                                defaultValue = -1L
                            },
                        )
                    ) {
                        GroupScreen(
                            navController = navController
                        )
                    }
                    composable(
                        route = Routing.AddEditGroupScreen.route + "?groupId={groupId}",
                        arguments = listOf(
                            navArgument(name = "groupId") {
                                type = NavType.LongType
                                defaultValue = -1L
                            },
                        )
                    ) {
                        AddEditGroupScreen(
                            navController = navController
                        )
                    }
                    composable(
                        route = Routing.TaskScreen.route + "?groupId={groupId}&taskId={taskId}",
                        arguments = listOf(
                            navArgument(name = "groupId") {
                                type = NavType.LongType
                                defaultValue = -1L
                            },
                            navArgument(name = "taskId") {
                                type = NavType.LongType
                                defaultValue = -1L
                            },
                        )
                    ) {
                        TaskScreen(
                            navController = navController
                        )
                    }
                    composable(
                        route = Routing.SolutionScreen.route + "?groupId={groupId}&taskId={taskId}&solutionId={solutionId}",
                        arguments = listOf(
                            navArgument(name = "groupId") {
                                type = NavType.LongType
                                defaultValue = -1L
                            },
                            navArgument(name = "taskId") {
                                type = NavType.LongType
                                defaultValue = -1L
                            },
                            navArgument(name = "solutionId") {
                                type = NavType.LongType
                                defaultValue = -1L
                            },
                        )
                    ) {
                        SolutionScreen(
                            navController = navController
                        )
                    }
                    composable(
                        route = Routing.GroupChatScreen.route + "?groupId={groupId}",
                        arguments = listOf(
                            navArgument(name = "groupId") {
                                type = NavType.LongType
                                defaultValue = -1L
                            },
                        )
                    ) {
                        GroupChatScreen(
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}