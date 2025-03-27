package com.example.syncro.presentation.ui.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import com.example.syncro.application.ui.theme.SyncroTheme

@Composable
fun CheckTextButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    text: String,
    textColor: Color = Color.Unspecified,
    arrangement: Arrangement.Horizontal = Arrangement.Start,
    leftTextPosition: Boolean = false,
    fillMaxWidth: Boolean = true
) {
    Row(
        horizontalArrangement = arrangement,
        verticalAlignment = Alignment.CenterVertically,
        modifier = if(fillMaxWidth) Modifier.fillMaxWidth() else Modifier
    ) {
        if (!leftTextPosition) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
        TextBodyMedium(
            text = text,
            color = textColor
        )
        if (leftTextPosition) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
    }
}

@Composable
fun CheckTextButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    text: AnnotatedString,
    textColor: Color = Color.Unspecified,
    arrangement: Arrangement.Horizontal = Arrangement.Start
) {
    Row(
        horizontalArrangement = arrangement,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        TextBodyMedium(
            text = text,
            color = textColor
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    SyncroTheme {
        Column {
            var checked = true
            CheckTextButton(checked = checked, onCheckedChange = { checked = !checked }, text = "42")
        }
    }
}