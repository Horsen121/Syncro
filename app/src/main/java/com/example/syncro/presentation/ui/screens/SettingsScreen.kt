package com.example.syncro.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.syncro.R
import com.example.syncro.application.CurrentUser
import com.example.syncro.application.MainActivity
import com.example.syncro.application.Routing
import com.example.syncro.presentation.ui.components.TopBarText
import com.example.syncro.presentation.ui.elements.TextBodyMedium
import com.example.syncro.presentation.ui.elements.TextHeadMedium
import com.example.syncro.presentation.ui.elements.TextHeadSmall
import kotlin.system.exitProcess

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController
) {
    val openExitDialog = remember { mutableStateOf(false) }
    val openLogOutDialog = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarText(
                leftText = stringResource(R.string.back),
                centerText = stringResource(R.string.routing_settings),
                rightText = stringResource(R.string.settings_exit),
                navController = navController
            ) {
                openExitDialog.value = true
            }
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    0.dp,
                    paddingValues.calculateTopPadding() + 10.dp,
                    0.dp,
                    paddingValues.calculateBottomPadding()
                )
        ) {
            if (openExitDialog.value) {
                DatePickerDialog(
                    confirmButton = {
                        TextButton(
                            onClick = {
                                MainActivity().finish()
                                exitProcess(0)
                            }
                        ) {
                            TextBodyMedium(stringResource(R.string.ok))
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { openExitDialog.value = false }) {
                            TextBodyMedium(stringResource(R.string.cancel))
                        }
                    },
                    onDismissRequest = { openExitDialog.value = false },
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 10.dp)
                    ) {
                        TextBodyMedium("    " + stringResource(R.string.settings_exit_confirm))
                    }
                }
            }

            if (openLogOutDialog.value) {
                DatePickerDialog(
                    confirmButton = {
                        TextButton(
                            onClick = {
                                CurrentUser.name = ""
                                CurrentUser.email = ""
                                CurrentUser.id = 1L

                                openLogOutDialog.value = false
                                navController.navigate(Routing.LoginScreen.route)
                            }
                        ) {
                            TextBodyMedium(stringResource(R.string.ok))
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { openLogOutDialog.value = false }) {
                            TextBodyMedium(stringResource(R.string.cancel))
                        }
                    },
                    onDismissRequest = { openLogOutDialog.value = false }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 10.dp)
                    ) {
                        TextBodyMedium("    " + stringResource(R.string.settings_logout_confirm))
                    }
                }
            }

            Card(
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .wrapContentHeight()
                    .height(96.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    TextHeadMedium(CurrentUser.name)
                    Spacer(Modifier.height(4.dp))

                    TextHeadSmall(CurrentUser.email)
                    Spacer(Modifier.height(4.dp))

                    TextButton(
                        onClick = { openLogOutDialog.value = true }
                    ) {
                        TextHeadSmall(stringResource(R.string.settings_logout))
                    }
                }
            }
        }
    }
}