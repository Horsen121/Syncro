package com.example.syncro.presentation.ui.screens.group

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
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
import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.syncro.R
import com.example.syncro.presentation.ui.components.FileCard
import com.example.syncro.presentation.ui.components.TopBarText
import com.example.syncro.presentation.ui.elements.DrawChangeRow
import com.example.syncro.presentation.ui.elements.DropMenu
import com.example.syncro.presentation.ui.elements.SimpleTextField
import com.example.syncro.presentation.ui.elements.TextBodyMedium
import com.example.syncro.presentation.ui.elements.TextHeadMedium
import com.example.syncro.presentation.ui.elements.TextHeadSmall
import com.example.syncro.presentation.viewmodels.group.TaskViewModel
import com.example.syncro.utils.TaskDifficult
import com.example.syncro.utils.toNormalString
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    navController: NavController,
    viewModel: TaskViewModel = hiltViewModel()
) {
    val openDateStartDialog = remember { mutableStateOf(false) }
    val openTimeStartDialog = remember { mutableStateOf(false) }

    val openDateEndDialog = remember { mutableStateOf(false) }
    val openTimeEndDialog = remember { mutableStateOf(false) }

    val openDateReminderDialog = remember { mutableStateOf(false) }
    val openTimeReminderDialog = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarText(
            leftText = stringResource(R.string.cancel),
            centerText = stringResource(R.string.task_title),
            rightText = stringResource(R.string.clear),
            navController = navController,
            rightAction = { viewModel.clear() }
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
                SimpleTextField(
                    value = viewModel.name.value,
                    placeholder = { TextBodyMedium(stringResource(R.string.task_name_place)) },
                    onValueChange = { viewModel.onNameChange(it) },
                    maxLength = 30
                )
                Spacer(modifier = Modifier.height(12.dp))

                TextHeadSmall(
                    text = stringResource(R.string.task_description_title)
                )
                SimpleTextField(
                    value = viewModel.desc.value,
                    placeholder = { TextBodyMedium(stringResource(R.string.task_description_place)) },
                    onValueChange = { viewModel.onDescChange(it) },
                    maxLength = 100
                )
                Spacer(modifier = Modifier.height(24.dp))

                DropMenu(
                    text = stringResource(R.string.task_difficulty),
                    elements = TaskDifficult.entries.map { el -> el.name },
                    current = viewModel.diff.value.ordinal,
                    open = viewModel.diffOpen.value,
                    onClick = { viewModel.onDiffOpenChange(it) },
                    onValueSelect = {
                        viewModel.onDiffChange(TaskDifficult.valueOf(it))
                    },
                    modifier = Modifier.fillMaxWidth(0.9f)
                )
                Spacer(modifier = Modifier.height(12.dp))

                // Time of Task
                var dateStartState by remember { mutableStateOf(viewModel.startTime.value) }
                val pickerDateStartState = remember {DatePickerState(
                    initialSelectedDateMillis = dateStartState?.toEpochSecond(ZoneOffset.UTC),
                    locale = CalendarLocale(Locale.current.language)
                ) }
                val pickerTimeStartState = remember { TimePickerState(
                    initialHour = dateStartState?.hour ?: LocalTime.now().hour,
                    initialMinute = dateStartState?.minute ?: LocalTime.now().minute,
                    is24Hour = true
                )}
                if (openDateStartDialog.value) {
                    DatePickerDialog(
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    dateStartState = LocalDateTime.of(
                                        LocalDateTime.ofEpochSecond(
                                            pickerDateStartState.selectedDateMillis!! /1000,
                                            0, ZoneOffset.UTC).toLocalDate(),
                                        LocalTime.of(
                                            pickerTimeStartState.hour,
                                            pickerTimeStartState.minute
                                        )
                                    )
                                    openDateStartDialog.value = false
                                    openTimeStartDialog.value = true
                                }
                            ) {
                                TextBodyMedium(stringResource(R.string.ok))
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    dateStartState = viewModel.startTime.value
                                    openDateStartDialog.value = false
                                }
                            ) {
                                TextBodyMedium(stringResource(R.string.cancel))
                            }
                        },
                        onDismissRequest = { openDateStartDialog.value = false }
                    ) {
                        DatePicker(state = pickerDateStartState, modifier = Modifier.fillMaxSize())
                    }
                }
                if (openTimeStartDialog.value) {
                    DatePickerDialog(
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    dateStartState?.withHour(pickerTimeStartState.hour)
                                    dateStartState?.withMinute(pickerTimeStartState.minute)
                                    viewModel.onStartTimeChange(dateStartState)
                                    openTimeStartDialog.value = false
                                }
                            ) {
                                TextBodyMedium(stringResource(R.string.ok))
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    dateStartState = viewModel.startTime.value
                                    openTimeStartDialog.value = false
                                    openDateStartDialog.value = true
                                }
                            ) {
                                TextBodyMedium(stringResource(R.string.cancel))
                            }
                        },
                        onDismissRequest = { openTimeStartDialog.value = false }
                    ) {
                        TimePicker(state = pickerTimeStartState, modifier = Modifier.fillMaxSize())
                    }
                }
                DrawChangeRow(
                    label = stringResource(id = R.string.task_start),
                    value = if (viewModel.startTime.value != null) viewModel.startTime.value!!.toNormalString()
                        .replace('-', '.').replace("T", "  ") else "",
                    height = 48.dp
                ) {
                    openDateStartDialog.value = true
                }
                Spacer(modifier = Modifier.height(12.dp))

                // Time of end Task
                var dateEndState by remember { mutableStateOf(viewModel.endTime.value) }
                val pickerDateEndState = remember {DatePickerState(
                    initialSelectedDateMillis = dateEndState?.toEpochSecond(ZoneOffset.UTC),
                    locale = CalendarLocale(Locale.current.language)
                ) }
                val pickerTimeEndState = remember { TimePickerState(
                    initialHour = dateEndState?.hour ?: LocalTime.now().hour,
                    initialMinute = dateEndState?.minute ?: LocalTime.now().minute,
                    is24Hour = true
                )}
                if (openDateEndDialog.value) {
                    DatePickerDialog(
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    dateEndState = LocalDateTime.of(
                                        LocalDateTime.ofEpochSecond(
                                            pickerDateEndState.selectedDateMillis!! /1000,
                                            0, ZoneOffset.UTC).toLocalDate(),
                                        LocalTime.of(
                                            pickerTimeEndState.hour,
                                            pickerTimeEndState.minute
                                        )
                                    )
                                    openDateEndDialog.value = false
                                    openTimeEndDialog.value = true
                                }
                            ) {
                                TextBodyMedium(stringResource(R.string.ok))
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    dateEndState = viewModel.startTime.value
                                    openDateEndDialog.value = false
                                }
                            ) {
                                TextBodyMedium(stringResource(R.string.cancel))
                            }
                        },
                        onDismissRequest = { openDateEndDialog.value = false }
                    ) {
                        DatePicker(state = pickerDateEndState, modifier = Modifier.fillMaxSize())
                    }
                }
                if (openTimeEndDialog.value) {
                    DatePickerDialog(
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    dateEndState?.withHour(pickerTimeEndState.hour)
                                    dateEndState?.withMinute(pickerTimeEndState.minute)

                                    if(dateEndState != null && dateStartState != null && dateEndState!! < dateStartState) {
                                        viewModel.onEndTimeChange(dateEndState)
                                    } else dateEndState = viewModel.endTime.value

                                    viewModel.onReminderTimeChange(
                                        dateEndState?.minusHours(1)
                                    )

                                    openTimeEndDialog.value = false
                                }
                            ) {
                                TextBodyMedium(stringResource(R.string.ok))
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    dateEndState = viewModel.endTime.value
                                    openTimeEndDialog.value = false
                                    openDateEndDialog.value = true
                                }
                            ) {
                                TextBodyMedium(stringResource(R.string.cancel))
                            }
                        },
                        onDismissRequest = { openTimeEndDialog.value = false }
                    ) {
                        TimePicker(state = pickerTimeEndState, modifier = Modifier.fillMaxSize())
                    }
                }
                DrawChangeRow(
                    label = stringResource(id = R.string.task_deadline),
                    value = if (viewModel.endTime.value != null) viewModel.endTime.value!!.toNormalString()
                        .replace('-', '.').replace("T", "  ") else "",
                    height = 48.dp
                ) {
                    openDateEndDialog.value = true
                }
                Spacer(modifier = Modifier.height(12.dp))

                // Time of end Task
                var dateReminderState by remember { mutableStateOf(viewModel.reminderTime.value) }
                val pickerDateReminderState = remember { DatePickerState(
                    initialSelectedDateMillis = dateReminderState?.toEpochSecond(ZoneOffset.UTC),
                    locale = CalendarLocale(Locale.current.language)
                ) }
                val pickerTimeReminderState = remember { TimePickerState(
                    initialHour = dateReminderState?.hour ?: LocalTime.now().hour,
                    initialMinute = dateReminderState?.minute ?: LocalTime.now().minute,
                    is24Hour = true
                )}
                if (openDateReminderDialog.value) {
                    DatePickerDialog(
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    dateReminderState = LocalDateTime.of(
                                        LocalDateTime.ofEpochSecond(
                                            pickerDateReminderState.selectedDateMillis!! /1000,
                                            0, ZoneOffset.UTC).toLocalDate(),
                                        LocalTime.of(
                                            pickerTimeReminderState.hour,
                                            pickerTimeReminderState.minute
                                        )
                                    )
                                    openDateReminderDialog.value = false
                                    openTimeReminderDialog.value = true
                                }
                            ) {
                                TextBodyMedium(stringResource(R.string.ok))
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    dateReminderState = viewModel.reminderTime.value
                                    openDateReminderDialog.value = false
                                }
                            ) {
                                TextBodyMedium(stringResource(R.string.cancel))
                            }
                        },
                        onDismissRequest = { openDateReminderDialog.value = false }
                    ) {
                        DatePicker(state = pickerDateReminderState, modifier = Modifier.fillMaxSize())
                    }
                }
                if (openTimeReminderDialog.value) {
                    DatePickerDialog(
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    dateReminderState?.withHour(pickerTimeReminderState.hour)
                                    dateReminderState?.withMinute(pickerTimeReminderState.minute)
                                    viewModel.onReminderTimeChange(dateReminderState)
                                    openTimeReminderDialog.value = false
                                }
                            ) {
                                TextBodyMedium(stringResource(R.string.ok))
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    dateReminderState = viewModel.reminderTime.value
                                    openTimeReminderDialog.value = false
                                    openDateReminderDialog.value = true
                                }
                            ) {
                                TextBodyMedium(stringResource(R.string.cancel))
                            }
                        },
                        onDismissRequest = { openTimeReminderDialog.value = false }
                    ) {
                        TimePicker(state = pickerTimeReminderState, modifier = Modifier.fillMaxSize())
                    }
                }
                DrawChangeRow(
                    label = stringResource(id = R.string.task_reminder_date),
                    value = if (viewModel.reminderTime.value != null) viewModel.reminderTime.value!!.toNormalString()
                        .replace('-', '.').replace("T", "  ") else "",
                    height = 48.dp
                ) {
                    openDateReminderDialog.value = true
                }
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
                    items(viewModel.files.value) { file ->
                        FileCard(
                            file,
                            onClick = { viewModel.onFileRemove(file) }
                        )
                    }
                }
                Spacer(Modifier.height(24.dp))

                val getFile = rememberLauncherForActivityResult(
                    ActivityResultContracts.StartActivityForResult()
                ) { result ->
                    result.let { viewModel.onFileAdd(it.data?.data?.path!!) }
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