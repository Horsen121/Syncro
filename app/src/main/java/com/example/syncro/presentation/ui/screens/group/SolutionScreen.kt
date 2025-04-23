package com.example.syncro.presentation.ui.screens.group

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.syncro.R
import com.example.syncro.application.ui.theme.SyncroTheme
import com.example.syncro.presentation.ui.components.FileCard
import com.example.syncro.presentation.ui.components.TopBarText
import com.example.syncro.presentation.ui.elements.SimpleTextField
import com.example.syncro.presentation.ui.elements.TextBodyMedium
import com.example.syncro.presentation.ui.elements.TextHeadMedium
import com.example.syncro.presentation.ui.elements.TextHeadSmall
import com.example.syncro.presentation.viewmodels.group.SolutionViewModel

@Composable
fun SolutionScreen(
    navController: NavController,
    viewModel: SolutionViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarText(
            leftText = stringResource(R.string.cancel),
            centerText = stringResource(R.string.solution_title),
            rightText = stringResource(R.string.clear),
            navController = navController,
            rightAction = { viewModel.clear() }
        ) }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    10.dp,
                    paddingValues.calculateTopPadding(),
                    10.dp,
                    paddingValues.calculateBottomPadding()
                )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
            ) {
                TextHeadSmall(
                    text = stringResource(R.string.solution_title_title)
                )
                SimpleTextField(
                    value = viewModel.name.value,
                    placeholder = { TextBodyMedium(stringResource(R.string.solution_title_place)) },
                    onValueChange = { viewModel.onNameChange(it) },
                    maxLength = 30
                )
                Spacer(modifier = Modifier.height(12.dp))

                TextHeadSmall(
                    text = stringResource(R.string.solution_description_title)
                )
                SimpleTextField(
                    value = viewModel.desc.value,
                    placeholder = { TextBodyMedium(stringResource(R.string.solution_description_place)) },
                    onValueChange = { viewModel.onDescChange(it) },
                    maxLength = 100
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextHeadSmall(text = stringResource(R.string.solution_files))
                }

                LazyColumn {
                    items(viewModel.sources.value) { source ->
                        FileCard(
                            source,
                            onClick = { viewModel.onSourceRemove(source) }
                        )
                    }
                }
                Spacer(Modifier.height(24.dp))

                val getFile = rememberLauncherForActivityResult(
                    ActivityResultContracts.StartActivityForResult()
                ) { result ->
                    result.let { viewModel.onSourceAdd(it.data?.data?.path!!) }
                }
                Button(
                    onClick = {
                        getFile.launch(Intent(Intent.ACTION_PICK))
                    }
                ) {
                    Image(Icons.Default.Add, null)
                }
            }
            Button(
                onClick = {
                    viewModel.onSave().also {
                        navController.navigateUp()
                    }
                }
            ) {
                TextHeadMedium(stringResource(R.string.add_edit_group_button))
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun SolutionScreenPreview() {
    SyncroTheme {
        SolutionScreen(
            rememberNavController(),
        )
    }
}