package com.example.syncro.application

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
import com.example.syncro.presentation.ui.screens.group.AddEditGroupScreen
import com.example.syncro.presentation.ui.screens.group.GroupScreen
import com.example.syncro.presentation.ui.screens.logreg.LoginScreen
import com.example.syncro.presentation.ui.screens.logreg.RegistrationScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SyncroTheme {
                var currentScreen by remember { mutableStateOf(Routing.LoginScreen.route) } // TODO: change to condition

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routing.GroupScreen.route // TODO: change to condition
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
                    	    navController = navController,
                            savedInstanceState = it.savedStateHandle
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
                            navController = navController,
                            savedInstanceState = it.savedStateHandle
                        )
                    }
                }
            }
        }
    }
}