package com.example.syncro.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.syncro.presentation.ui.elements.TextBodyMedium

@Composable
fun FileCard(
    path: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val file = path.split("/").last()

    Card(
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
        onClick = {  },
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .then(modifier)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(if (file.split(".").last() == "img") Icons.Default.AccountBox else Icons.Default.Create, null)
                Spacer(Modifier.width(12.dp))
                TextBodyMedium(file)
            }
            Image(Icons.Default.Clear, null,
                modifier = Modifier.clickable { onClick() }
            )
        }
    }
}