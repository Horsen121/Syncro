package com.example.syncro.presentation.ui.screens.group

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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.syncro.R
import com.example.syncro.application.Routing
import com.example.syncro.presentation.ui.components.FileCard
import com.example.syncro.presentation.ui.components.TopBarText
import com.example.syncro.presentation.ui.elements.DrawChangeRow
import com.example.syncro.presentation.ui.elements.TextHeadSmall
import com.example.syncro.presentation.viewmodels.group.TaskViewModel
import com.example.syncro.utils.toNormalString

@Composable
fun TaskScreen(
    navController: NavController,
    viewModel: TaskViewModel = hiltViewModel()
) {
    val name = viewModel.name.collectAsState()
    val desc = viewModel.desc.collectAsState()
    val startTime = viewModel.startTime.collectAsState()
    val endTime = viewModel.endTime.collectAsState()
    val reminderTime = viewModel.reminderTime.collectAsState()
    val diff = viewModel.diff.collectAsState()
    val files = viewModel.files.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarText(
            leftText = stringResource(R.string.back),
            centerText = stringResource(R.string.task_title),
            rightText = stringResource(R.string.edit),
            navController = navController,
            rightAction = { navController.navigate(Routing.AddEditTaskScreen.route +
                    "?groupId=${viewModel.groupId}&taskId=${viewModel.taskId!!}") }
        ) }
    ) { paddingValues ->
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
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
                    text = stringResource(R.string.task_name_title)
                )
                TextHeadSmall(
                    text = name.value,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(10.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))

                TextHeadSmall(
                    text = stringResource(R.string.task_description_title)
                )
                TextHeadSmall(
                    text = desc.value,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(10.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))

                DrawChangeRow(
                    label = stringResource(id = R.string.task_difficulty),
                    value = diff.value,
                    height = 48.dp
                ) { }
                Spacer(modifier = Modifier.height(12.dp))

                DrawChangeRow(
                    label = stringResource(id = R.string.task_start),
                    value = if (startTime.value != null) startTime.value!!.toNormalString()
                        .replace('-', '.').replace("T", "  ") else "",
                    height = 48.dp
                ) { }
                Spacer(modifier = Modifier.height(12.dp))

                DrawChangeRow(
                    label = stringResource(id = R.string.task_deadline),
                    value = if (endTime.value != null) endTime.value!!.toNormalString()
                        .replace('-', '.').replace("T", "  ") else "",
                    height = 48.dp
                ) { }
                Spacer(modifier = Modifier.height(12.dp))

                DrawChangeRow(
                    label = stringResource(id = R.string.task_reminder_date),
                    value = if (reminderTime.value != null) reminderTime.value!!.toNormalString()
                        .replace('-', '.').replace("T", "  ") else "",
                    height = 48.dp
                ) { }
                Spacer(modifier = Modifier.height(12.dp))


                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextHeadSmall(text = stringResource(R.string.task_files))
                }
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(files.value) { file ->
                        FileCard(
                            file,
                            onClick = {  }
                        )
                    }
                }
            }
        }
    }
}