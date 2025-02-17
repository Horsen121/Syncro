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
import com.example.syncro.presentation.viewmodels.group.TaskViewModel
import com.example.syncro.utils.TaskDifficult

@Composable
fun TaskScreen(
    navController: NavController,

) {
    val viewModel = TaskViewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.Start,
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
                    text = stringResource(R.string.task_title)
                )
                TextButton(onClick = { viewModel.Clear() }) {
                    TextBodyMedium(
                        text = stringResource(R.string.clear)
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            TextHeadSmall(
                text = stringResource(R.string.task_name_title)
            )
            SimpleTextField(
                value = viewModel.name.value,
                placeholder = { TextBodyMedium(stringResource(R.string.task_name_place)) },
                onValueChange = { viewModel.onNameChange(it) }
            )
            Spacer(modifier = Modifier.height(12.dp))

            TextHeadSmall(
                text = stringResource(R.string.task_description_title)
            )
            SimpleTextField(
                value = viewModel.name.value,
                placeholder = { TextBodyMedium(stringResource(R.string.task_description_place)) },
                onValueChange = { viewModel.onDescChange(it) }
            )
            Spacer(modifier = Modifier.height(24.dp))

            DropMenu(
                text = stringResource(R.string.task_difficulty),
                elements = TaskDifficult.entries.map { el -> el.name },
                current = TaskDifficult.Medium.ordinal,
                open = viewModel.diffOpen.value,
                onClick = { viewModel.onDiffOpenChange(it) },
                onValueSelect = {
                    viewModel.onDiffChange(TaskDifficult.valueOf(it))
                },
                modifier = Modifier.fillMaxWidth(0.9f)
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.clickable { } // TODO: open a fileManager
            ) {
                TextHeadSmall(text = stringResource(R.string.task_files))
                Image(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
            LazyColumn {
                items(viewModel.files.value) { file ->
                    // TODO: add element
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
fun TaskScreenPreview() {
    SyncroTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            TaskScreen(
                rememberNavController()
            )
        }
    }
}