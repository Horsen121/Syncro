package com.example.syncro.presentation.ui.screens.group

import android.annotation.SuppressLint
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.syncro.R
import com.example.syncro.application.ui.theme.SyncroTheme
import com.example.syncro.data.models.Group
import com.example.syncro.presentation.ui.components.TopBarBackButton
import com.example.syncro.presentation.ui.elements.CheckTextButton
import com.example.syncro.presentation.ui.elements.SimpleTextField
import com.example.syncro.presentation.ui.elements.TextBodyMedium
import com.example.syncro.presentation.ui.elements.TextHeadMedium
import com.example.syncro.presentation.ui.elements.TextHeadSmall
import com.example.syncro.presentation.viewmodels.group.AddEditGroupViewModel

@Composable
fun AddEditGroupScreen(
    navController: NavController,
    savedInstanceState: SavedStateHandle,
) {
    val viewModel = AddEditGroupViewModel(savedInstanceState)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarBackButton(text = viewModel.group.value?.name ?: stringResource(R.string.add_edit_group_title), navController) }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .padding(0.dp, paddingValues.calculateTopPadding() + 10.dp, 0.dp, paddingValues.calculateBottomPadding())
        ) {
            TextHeadSmall(
                text = stringResource(R.string.add_edit_group_name_title)
            )
            SimpleTextField(
                value = viewModel.name.value,
                placeholder = { TextBodyMedium(stringResource(R.string.add_edit_group_name_place)) },
                onValueChange = { viewModel.onNameChange(it) }
            )
            Spacer(modifier = Modifier.height(12.dp))

            TextHeadSmall(
                text = stringResource(R.string.add_edit_group_description_title)
            )
            SimpleTextField(
                value = viewModel.name.value,
                placeholder = { TextBodyMedium(stringResource(R.string.add_edit_group_description_place)) },
                onValueChange = { viewModel.onDescChange(it) }
            )
            Spacer(modifier = Modifier.height(24.dp))

            CheckTextButton(
                text = stringResource(R.string.add_edit_group_private),
                checked = viewModel.isPrivate.value,
                onCheckedChange = { viewModel.onPrivateChange() }
            )
        }
        Button(
            onClick = {  }
        ) {
            TextHeadMedium(stringResource(R.string.add_edit_group_button))
        }
    }
}

@Preview(
    showBackground = true,
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEditGroupScreenPreview() {
    SyncroTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            AddEditGroupScreen(
                rememberNavController(),
                savedInstanceState = SavedStateHandle(),
            )
        }
    }
}