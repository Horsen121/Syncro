package com.example.syncro.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.syncro.presentation.ui.elements.TextBodySmall

@Composable
fun SimpleBottomBar(
    options: List<Triple<String, () -> Unit, ImageVector>>,
) {
    NavigationBar(// TODO: change to beautiful
        modifier = Modifier
            .padding(10.dp)
            .clip(MaterialTheme.shapes.large)
    ) {
        options.forEachIndexed { _, item ->
            NavigationBarItem(
                selected = false,
                label = { TextBodySmall(item.first, textAlign = TextAlign.Center) },
                onClick = { item.second() },
                icon = { Image(item.third, null) }
            )
        }
    }
}