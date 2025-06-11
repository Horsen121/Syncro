package com.example.syncro.presentation.ui.screens.group

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.syncro.R
import com.example.syncro.presentation.ui.components.TopBarText
import com.example.syncro.presentation.ui.elements.CheckTextButton
import com.example.syncro.presentation.ui.elements.SimpleTextField
import com.example.syncro.presentation.ui.elements.TextBodyMedium
import com.example.syncro.presentation.ui.elements.TextHeadMedium
import com.example.syncro.presentation.ui.elements.TextHeadSmall
import com.example.syncro.presentation.viewmodels.group.AddEditGroupViewModel

@Composable
fun AddEditGroupScreen(
    navController: NavController,
    viewModel: AddEditGroupViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarText(
                leftText = stringResource(R.string.cancel),
                centerText = viewModel.name.value.ifEmpty { stringResource(R.string.add_edit_group_title) },
                rightText = stringResource(R.string.clear),
                navController = navController
            ) { viewModel.clear() }
        }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(
                    0.dp,
                    paddingValues.calculateTopPadding() + 10.dp,
                    0.dp,
                    paddingValues.calculateBottomPadding()
                )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
            ) {
                TextHeadSmall(
                    text = stringResource(R.string.add_edit_group_name_title)
                )
                SimpleTextField(
                    value = viewModel.name.value,
                    placeholder = { TextBodyMedium(stringResource(R.string.add_edit_group_name_place)) },
                    onValueChange = { viewModel.onNameChange(it) },
                    maxLength = 30
                )
                Spacer(modifier = Modifier.height(12.dp))

                TextHeadSmall(
                    text = stringResource(R.string.add_edit_group_description_title)
                )
                SimpleTextField(
                    value = viewModel.desc.value,
                    placeholder = { TextBodyMedium(stringResource(R.string.add_edit_group_description_place)) },
                    onValueChange = { viewModel.onDescChange(it) },
                    maxLength = 100
                )
                Spacer(modifier = Modifier.height(24.dp))

                CheckTextButton(
                    text = stringResource(R.string.add_edit_group_private),
                    checked = viewModel.isPrivate.value,
                    onCheckedChange = { viewModel.onPrivateChange() }
                )
                Spacer(modifier = Modifier.height(24.dp))

                CheckTextButton(
                    text = "isAdmin",
                    checked = viewModel.isAdmin.value,
                    onCheckedChange = { viewModel.onAdminChange() }
                )
            }

            val context = LocalContext.current
            Button(
                onClick = {
                    viewModel.onSave().also {
                        if(viewModel.response.value) {
                            navController.navigateUp()
                        } else {
                            Toast.makeText(context, viewModel.error.value, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            ) {
                TextHeadMedium(stringResource(R.string.add_edit_group_button))
            }
        }
    }
}