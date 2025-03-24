package com.example.syncro.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.syncro.data.models.Task
import com.example.syncro.presentation.ui.elements.TextBodyMedium

@Composable
fun GroupTask(
    task: Task,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.then(Modifier.fillMaxWidth()),
        onClick = { onClick() }
    ) {
        TextBodyMedium(task.title)
    }
}