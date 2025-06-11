package com.example.syncro.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.syncro.application.ui.theme.SyncroTheme
import com.example.syncro.data.models.Group
import com.example.syncro.presentation.ui.elements.TextBodyMedium
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
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(10.dp, 0.dp)
            ) {
                TextHeadMedium(
                    text = group.name,
                    textAlign = TextAlign.Start,
                    fillMaxWidth = false
                )
                TextBodyMedium(
                    text = group.description ?: "",
                    textAlign = TextAlign.Start,
                )
            }
            Spacer(Modifier.width(24.dp))

            Row {
                TextBodyMedium(group.countPeople.toString())
                Image(Icons.Default.Person, null)
            }
        }
    }
}

@Preview
@Composable
private fun GroupListElementPreview() {
    SyncroTheme {
        Scaffold { _ ->
            GroupListElement(
                group = Group(1, "Test1", "desk", 10, 1L,true),
                onClick = {  }
            )
        }
    }
}