package com.example.syncro.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.syncro.application.ui.theme.SyncroTheme
import com.example.syncro.data.models.Solution
import com.example.syncro.presentation.ui.elements.TextBodyMedium

@Composable
fun SolutionListElement(
    solution: Solution,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        modifier = modifier
            .fillMaxWidth()
    ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp)
            ) {
                TextBodyMedium(
                    text = solution.title,
                    textAlign = TextAlign.Start,
                )
                TextBodyMedium(
                    text = solution.description,
                    textAlign = TextAlign.Start,
                )
            }
    }
}

@Preview
@Composable
private fun SolutionListElementPreview() {
    SyncroTheme {
        Scaffold { _ ->
            SolutionListElement(
                solution = Solution(1, 1, 1, 1,"Solution1", "asdgdsfasd"),
                onClick = {  }
            )
        }
    }
}