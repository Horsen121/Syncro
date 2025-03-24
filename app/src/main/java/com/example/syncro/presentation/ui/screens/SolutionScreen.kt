package com.example.syncro.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.syncro.R
import com.example.syncro.application.ui.theme.SyncroTheme
import com.example.syncro.presentation.ui.components.TopBarText
import com.example.syncro.presentation.ui.elements.DropMenu
import com.example.syncro.presentation.ui.elements.SimpleTextField
import com.example.syncro.presentation.ui.elements.TextBodyMedium
import com.example.syncro.presentation.ui.elements.TextHeadMedium
import com.example.syncro.presentation.ui.elements.TextHeadSmall
import com.example.syncro.presentation.viewmodels.SolutionViewModel

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
            DropMenu(
                text = stringResource(R.string.solution_title) + " ${viewModel.name.value}",
                elements = viewModel.tasks.value,
                current = viewModel.tasks.value.indexOf(viewModel.task.value),
                open = viewModel.open.value,
                onClick = { viewModel.onOpenChange(it) },
                onValueSelect = {
                    viewModel.onTaskChange(it)
                },
                modifier = Modifier.fillMaxWidth(0.9f)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.clickable { } // TODO: open a fileManager
            ) {
                TextHeadSmall(text = stringResource(R.string.solution_files))
                Image(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }

            TextHeadSmall(
                text = stringResource(R.string.solution_description_title)
            )
            SimpleTextField(
                value = viewModel.desc.value,
                placeholder = { TextBodyMedium(stringResource(R.string.solution_description_place)) },
                onValueChange = { viewModel.onDescChange(it) }
            )
            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn {
                items(viewModel.sources.value) {source ->
                    // TODO: add elem
                }
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