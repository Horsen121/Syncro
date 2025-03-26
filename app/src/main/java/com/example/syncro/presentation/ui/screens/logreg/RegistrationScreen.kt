package com.example.syncro.presentation.ui.screens.logreg

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.syncro.R
import com.example.syncro.application.Routing
import com.example.syncro.application.ui.theme.SyncroTheme
import com.example.syncro.presentation.ui.elements.CheckTextButton
import com.example.syncro.presentation.ui.elements.PasswordTextField
import com.example.syncro.presentation.ui.elements.SimpleTextField
import com.example.syncro.presentation.ui.elements.TextBodyMedium
import com.example.syncro.presentation.ui.elements.TextHeadMedium
import com.example.syncro.presentation.ui.elements.TextHeadSmall
import com.example.syncro.presentation.viewmodels.logreg.RegistrationViewModel

@Composable
fun RegistrationScreen(
    navController: NavController,
) {
    val viewModel = RegistrationViewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, paddingValues.calculateTopPadding(), 20.dp, paddingValues.calculateBottomPadding())
        ) {
            TextHeadMedium(
                text = stringResource(id = R.string.reg_hello),
                textAlign = TextAlign.Start
            )
            TextBodyMedium(text = stringResource(id = R.string.reg_desc))
            Spacer(modifier = Modifier.height(12.dp))

            TextHeadSmall(
                text = stringResource(id = R.string.reg_name_head),
                textAlign = TextAlign.Start
            )
            SimpleTextField(
                value = viewModel.name.value,
                onValueChange = { viewModel.onNameChange(it) },
                placeholder = { TextBodyMedium(text = stringResource(id = R.string.reg_name_pl)) }
            )
            Spacer(modifier = Modifier.height(12.dp))

            TextHeadSmall(
                text = stringResource(id = R.string.reg_email_head),
                textAlign = TextAlign.Start
            )
            SimpleTextField(
                value = viewModel.email.value,
                onValueChange = { viewModel.onEmailChange(it) },
                placeholder = { TextBodyMedium(text = stringResource(id = R.string.reg_email_pl)) }
            )
            Spacer(modifier = Modifier.height(12.dp))

            TextHeadSmall(
                text = stringResource(id = R.string.reg_pass_head),
                textAlign = TextAlign.Start
            )
            PasswordTextField(
                value = viewModel.password1.value,
                onValueChange = { viewModel.onPassword1Change(it) },
                placeholder = { TextBodyMedium(text = stringResource(id = R.string.reg_pass_pl1)) }
            )
            Spacer(modifier = Modifier.height(12.dp))
            PasswordTextField(
                value = viewModel.password2.value,
                onValueChange = { viewModel.onPassword2Change(it) },
                placeholder = { TextBodyMedium(text = stringResource(id = R.string.reg_pass_pl2)) }
            )
            Spacer(modifier = Modifier.height(24.dp))

            CheckTextButton(
                checked = viewModel.agreement.value,
                onCheckedChange = { viewModel.onAgreementChange() },
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.reg_agreement_1) + " ")
                    withStyle(SpanStyle(color = Color.Blue)) {
                        addLink(LinkAnnotation.Url(""), 1, 2) // TODO: add link to Req doc
                        append(stringResource(id = R.string.reg_agreement_2) + " ")
                    }
                    append(stringResource(id = R.string.reg_agreement_3) + " ")
                    withStyle(SpanStyle(color = Color.Blue)) {
                        addLink(LinkAnnotation.Url(""), 1, 2)  // TODO: add link to Policy doc
                        append(stringResource(id = R.string.reg_agreement_4))
                    }
                }
            )
            Spacer(modifier = Modifier.height(48.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { navController.navigate(Routing.LoginScreen.route) }) {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        color = MaterialTheme.colorScheme.background
                    )
                }
                Button(
                    onClick = {
                        viewModel.registration()
                        navController.navigate(Routing.LoginScreen.route)
                              },
                    enabled = viewModel.agreement.value
                ) {
                    Text(
                        text = stringResource(id = R.string.reg_button_text),
                        color = MaterialTheme.colorScheme.background
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
//    showSystemUi = true,
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegistrationScreenPreview() {
    SyncroTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            RegistrationScreen(
                rememberNavController()
            )
        }
    }
}