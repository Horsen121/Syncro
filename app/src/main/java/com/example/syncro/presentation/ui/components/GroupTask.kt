package com.example.syncro.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.syncro.R
import com.example.syncro.application.ui.theme.SyncroTheme
import com.example.syncro.data.models.Task
import com.example.syncro.presentation.ui.elements.TextBodyMedium
import com.example.syncro.presentation.ui.elements.TextHeadSmall
import com.example.syncro.utils.TaskDifficult

@Composable
fun GroupTask(
    task: Task,
    onClick: () -> Unit,
    onSolutionClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, when (task.priority) {
                TaskDifficult.Easy.name -> { Color.Green }
                TaskDifficult.Medium.name -> { Color.Yellow }
                TaskDifficult.Hard.name -> { Color.Red }
                else -> { Color.Black }
            }
        ),
        modifier = modifier.then(Modifier.fillMaxWidth())
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                TextHeadSmall(task.title, fillMaxWidth = false)
                TextBodyMedium(task.description, maxLines = 2)
            }
            Spacer(Modifier.width(24.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { onSolutionClick() }
            ) {
                TextBodyMedium(stringResource(R.string.card_task_solutions))
                Image(Icons.Default.Create, null)
            }
        }
    }
}

@Preview
@Composable
private fun GroupListElementPreview() {
    SyncroTheme {
        Scaffold { _ ->
            GroupTask(
                task = Task(1, 1, "Task", "desk", 1, "25/03/25","31/03/25", TaskDifficult.Easy.name),
                onClick = {  },
                onSolutionClick = {  }
            )
        }
    }
}