package com.example.syncro.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.syncro.presentation.ui.elements.TextBodySmall
import kotlinx.coroutines.CoroutineScope

@Composable
fun SimpleBottomBar(
    options: List<Triple<String, () -> Unit, ImageVector>>,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    NavigationBar(// TODO: change to beautiful
        modifier = Modifier
            .padding(10.dp)
            .clip(MaterialTheme.shapes.large)
    ) {
        LazyRow {
            items(options) { item ->
                NavigationBarItem(
                    selected = false,
                    label = { TextBodySmall(item.first) },
                    onClick = { item.second },
                    icon = { Image(item.third, null) }
                )
            }
        }
    }
}