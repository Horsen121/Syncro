package com.example.login.presentation

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.feature.login.R
import com.example.ui.components.PasswordTextField
import com.example.ui.components.SimpleTextField
import com.example.ui.components.TextBodyMedium
import com.example.ui.components.TextHeadLarge
import com.example.ui.components.TextHeadSmall

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onRegistrationClick: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is LoginUiEvent.NavigateToHome -> onLoginSuccess()
                is LoginUiEvent.ShowToast -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

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
                    value = uiState.email,
                    onValueChange = { viewModel.onEmailChange(it) },
                    placeholder = { TextBodyMedium(text = stringResource(id = R.string.login_placeholder_email)) },
                    modifier = Modifier.padding(5.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))

                PasswordTextField(
                    value = uiState.password,
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

            Button(
                onClick = { viewModel.signIn() },
                enabled = !uiState.isLoading,
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
            TextButton(onClick = { onRegistrationClick() }) {
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