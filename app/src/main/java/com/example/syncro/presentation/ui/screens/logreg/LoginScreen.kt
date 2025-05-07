package com.example.syncro.presentation.ui.screens.logreg

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.syncro.R
import com.example.syncro.application.Routing
import com.example.syncro.presentation.ui.elements.PasswordTextField
import com.example.syncro.presentation.ui.elements.SimpleTextField
import com.example.syncro.presentation.ui.elements.TextBodyMedium
import com.example.syncro.presentation.ui.elements.TextHeadLarge
import com.example.syncro.presentation.ui.elements.TextHeadSmall
import com.example.syncro.presentation.viewmodels.logreg.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    0.dp,
                    paddingValues.calculateTopPadding(),
                    0.dp,
                    paddingValues.calculateBottomPadding()
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_image),
                contentScale = ContentScale.FillWidth, // Crop
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight(0.35f)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(12.dp))

            TextHeadLarge(text = stringResource(id = R.string.login_hello))

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(10.dp, 20.dp)
            ) {
                SimpleTextField(
                    value = viewModel.login.value,
                    onValueChange = { viewModel.onLoginChange(it) },
                    placeholder = { TextBodyMedium(text = stringResource(id = R.string.login_placeholder_email)) },
                    modifier = Modifier.padding(5.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))

                PasswordTextField(
                    value = viewModel.password.value,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    placeholder = { TextBodyMedium(text = stringResource(id = R.string.login_placeholder_password)) },
                    modifier = Modifier.padding(5.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))


                TextButton(onClick = { viewModel.passwordChange() }) {
                    TextBodyMedium(
                        text = stringResource(id = R.string.login_forgot),
                        color = Color.Blue
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
            }

            val context = LocalContext.current
            Button(
                onClick = {
//                    viewModel.signIn()
//                    if (viewModel.response.value != "")
//                        Toast.makeText(context, viewModel.response.value, Toast.LENGTH_LONG).show()
                         navController.navigate(Routing.GroupsScreen.route)
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ) {
                TextHeadSmall(
                    text = stringResource(id = R.string.login_button_text),
                    color = MaterialTheme.colorScheme.background
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            TextBodyMedium(text = stringResource(id = R.string.login_register_text))
            TextButton(onClick = { navController.navigate(Routing.RegistrationScreen.route) }) {
                TextBodyMedium(
                    text = stringResource(id = R.string.login_register_link),
                    color = Color.Blue
                )
            }

            HorizontalDivider()
            Spacer(modifier = Modifier.height(12.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextBodyMedium(text = stringResource(id = R.string.login_continue_variants))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                ) {
                    IconButton(
                        onClick = { viewModel.signInWithGoogle() }
                    ) {
                        Image(
                            imageVector = Icons.Default.AccountCircle,
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(if (isSystemInDarkTheme()) Color.White else Color.Black),
                            modifier = Modifier
                                .height(48.dp)
                        )
                    }
                }
            }
        }
    }
}