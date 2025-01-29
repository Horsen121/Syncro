package com.example.syncro.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.syncro.R
import com.example.syncro.application.ui.theme.SyncroTheme
import com.example.syncro.presentation.ui.models.elements.TextBodyLarge
import com.example.syncro.presentation.ui.models.elements.TextHeadSmall
import com.example.syncro.presentation.viewmodels.LoginViewModel

@Composable
fun LoginScreen(
//    navController:
) {
    val viewModel = LoginViewModel()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight(0.35f)
        )
        Spacer(modifier = Modifier.height(12.dp))

        TextHeadSmall(text = stringResource(id = R.string.login_hello))

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(10.dp, 20.dp)
        ) {
            TextField(
                value = viewModel.login.value,
                onValueChange = { viewModel.onLoginChange(it) },
                placeholder = { TextBodyLarge(text = stringResource(id = R.string.login_placeholder_email)) }
            )
            Spacer(modifier = Modifier.height(12.dp))

            TextField(
                value = viewModel.password.value,
                onValueChange = { viewModel.onPasswordChange(it) },
                placeholder = { TextBodyLarge(text = stringResource(id = R.string.login_placeholder_password)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = { (Icon(imageVector = Icons.Default.Lock, contentDescription = null)) }
            )
            Spacer(modifier = Modifier.height(12.dp))


            TextBodyLarge(text = stringResource(id = R.string.login_forgot))
            Spacer(modifier = Modifier.height(12.dp))
        }

        Button(
            onClick = { viewModel.signIn() },
            modifier = Modifier
                .fillMaxWidth(0.8f)
        ) {
            TextHeadSmall(text = stringResource(id = R.string.login_button_text), color = MaterialTheme.colorScheme.background)
        }
        Spacer(modifier = Modifier.height(12.dp))

        TextBodyLarge(text = stringResource(id = R.string.login_register_text))
        TextButton(onClick = { /*TODO*/ }) {
            TextBodyLarge(text = stringResource(id = R.string.login_register_link), color = Color.Blue)
        }

        HorizontalDivider()
        Spacer(modifier = Modifier.height(12.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextBodyLarge(text = stringResource(id = R.string.login_continue_variants))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ) {
                Image(
                    imageVector = Icons.Default.AccountCircle,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .height(48.dp)
                )
            }
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun LoginScreenPreview() {
    SyncroTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            LoginScreen()
        }
    }
}