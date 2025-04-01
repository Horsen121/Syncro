package com.example.syncro.presentation.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DrawChangeRow(
    label: String,
    value: String,
    height: Dp,
    onClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .height(height)
    ) {
        TextHeadSmall(
            text = label,
            modifier = Modifier.padding(start = 8.dp),
            fillMaxWidth = false
        )
        Row(verticalAlignment = Alignment.Top) {
            TextBodyMedium(
                text = value,
                modifier = Modifier.padding(end = 8.dp)
            )
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null)
        }
    }
}