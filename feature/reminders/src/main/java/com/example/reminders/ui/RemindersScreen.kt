package com.example.reminders.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.reminders.presentation.RemindersViewModel
import com.example.ui.elements.TopBarBackButton
import com.example.reminders.R

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun RemindersScreen(
    backNavigate: () -> Unit,
    navigateToTaskScreen: (Long) -> Unit,
    viewModel: RemindersViewModel = hiltViewModel()
) {
    val reminders = viewModel.reminders.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarBackButton(stringResource(R.string.reminders_routing), backNavigate) }
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
            LazyColumn {
                items(reminders.value) { reminder ->
                    ReminderListElement(
                        reminder = reminder,
                        onClick = { navigateToTaskScreen(reminder.first.task_id) }
                    )
                }
            }
        }
    }
}