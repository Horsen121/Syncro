package com.example.syncro.presentation.ui.screens.group

import android.annotation.SuppressLint
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.syncro.R
import com.example.syncro.application.ui.theme.SyncroTheme
import com.example.syncro.presentation.ui.elements.DropMenu
import com.example.syncro.presentation.ui.elements.SimpleTextField
import com.example.syncro.presentation.ui.elements.TextBodyMedium
import com.example.syncro.presentation.ui.elements.TextHeadMedium
import com.example.syncro.presentation.ui.elements.TextHeadSmall
import com.example.syncro.presentation.viewmodels.group.SolutionViewModel
import com.example.syncro.utils.TaskDifficult

@Composable
fun SolutionScreen(
    navController: NavController,

) {
    val viewModel = SolutionViewModel()
    Scaffold(
        modifier = Modifier.fillMaxSize()
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
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = { navController.navigateUp() }) {
                    TextBodyMedium(
                        text = stringResource(R.string.cancel),
                        color = Color.Blue
                    )
                }
                TextHeadMedium(
                    text = stringResource(R.string.solution_title)
                )
                TextButton(onClick = { viewModel.clear() }) {
                    TextBodyMedium(
                        text = stringResource(R.string.clear)
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

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
                value = viewModel.name.value,
                placeholder = { TextBodyMedium(stringResource(R.string.solution_description_place)) },
                onValueChange = { viewModel.onNameChange(it) }
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