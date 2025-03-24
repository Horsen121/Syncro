package com.example.syncro.presentation.ui.components

import androidx.compose.foundation.BorderStroke
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
import com.example.syncro.data.models.Group
import com.example.syncro.presentation.ui.elements.TextHeadMedium

@Composable
fun GroupListElement(
    group: Group,
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
            .fillMaxWidth(0.9f)
    ) {
        TextHeadMedium(
            text = group.name,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(10.dp, 0.dp)
        )
    }
}

@Preview
@Composable
private fun GroupListElementPreview() {
    SyncroTheme {
        Scaffold { _ ->
            GroupListElement(
                group = Group(1, "Test1", "desk", "", 1L, true),
                onClick = {  }
            )
        }
    }
}