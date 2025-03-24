package com.example.syncro.presentation.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DropMenu(
    text: String,
    elements: List<String>,
    current: Int,
    open: Boolean,
    onClick: (Boolean) -> Unit,
    onValueSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.then(Modifier
            .fillMaxWidth()
            .clickable { onClick(!open) })
    ) {
        TextBodyMedium(text = text)
        TextBodyMedium(text = elements[current])
    }
    if (open) {
        LazyColumn(
            horizontalAlignment = Alignment.End,
            contentPadding = PaddingValues(top = 10.dp, end = 20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(elements) {elem ->
                TextBodyMedium(
                    text = elem,
                    modifier = Modifier.clickable {
                        onValueSelect(elem)
                        onClick(false)
                    }
                )
            }
        }
    }
}