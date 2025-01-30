package com.example.syncro.presentation.ui.models.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.syncro.application.ui.theme.SyncroTheme

@Composable
fun TextHeadLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign? = TextAlign.Center,
    fillMaxWidth: Boolean = true
) {
    Text(
        text = text,
        color = color,
        textAlign = textAlign,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineMedium,
        modifier = if(fillMaxWidth) modifier.fillMaxWidth() else modifier
    )
}

@Composable
fun TextHeadMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign? = TextAlign.Center,
    fillMaxWidth: Boolean = true
) {
    Text(
        text = text,
        color = color,
        textAlign = textAlign,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineSmall,
        modifier = if(fillMaxWidth) modifier.fillMaxWidth() else modifier
    )
}

@Composable
fun TextHeadSmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign? = TextAlign.Center,
    fillMaxWidth: Boolean = true
) {
    Text(
        text = text,
        color = color,
        textAlign = textAlign,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.bodyLarge,
        modifier = if(fillMaxWidth) modifier.fillMaxWidth() else modifier
    )
}


@Composable
fun TextBodyLarge(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign? = TextAlign.Unspecified
) {
    Text(
        text = text,
        color = color,
        textAlign = textAlign,
        fontWeight = FontWeight.Normal,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
    )
}

@Composable
fun TextBodyMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign? = TextAlign.Unspecified
) {
    Text(
        text = text,
        color = color,
        textAlign = textAlign,
        fontWeight = FontWeight.Normal,
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier
    )
}

@Composable
fun TextBodySmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign? = TextAlign.Unspecified
) {
    Text(
        text = text,
        color = color,
        textAlign = textAlign,
        fontWeight = FontWeight.Normal,
        style = MaterialTheme.typography.titleSmall,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun HeadStylePreview() {
    SyncroTheme {
        Column {
            TextHeadLarge("TextHeadLarge")
            TextHeadMedium("TextHeadMedium")
            TextHeadSmall("TextHeadSmall")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BodyStylePreview() {
    SyncroTheme {
        Column {
            TextBodyLarge("TextBodyLarge")
            TextBodyMedium("TextBodyMedium")
            TextBodySmall("TextBodySmall")
        }
    }
}