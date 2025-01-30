package com.example.syncro.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.syncro.application.ui.theme.SyncroTheme
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

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Routing.LoginScreen.route) {
                        composable(route = Routing.LoginScreen.route) {
                            LoginScreen(
                                navController = navController,
                                paddingValues = innerPadding
                            )
                        }
                        composable(route = Routing.RegistrationScreen.route) {
                            RegistrationScreen(
                                navController = navController,
                                paddingValues = innerPadding
                            )
                        }
                    }


                }
            }
        }
    }
}