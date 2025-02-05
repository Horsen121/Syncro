package com.example.syncro.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.syncro.application.ui.theme.SyncroTheme
import com.example.syncro.presentation.ui.screens.GroupsScreen
import com.example.syncro.presentation.ui.screens.LoginScreen
import com.example.syncro.presentation.ui.screens.RegistrationScreen
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
                    
                }
            }
        }
    }
}