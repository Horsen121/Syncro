package com.example.syncro.presentation.ui.models.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.syncro.application.ui.theme.SyncroTheme
import com.example.syncro.presentation.ui.screens.LoginScreen

@Composable
fun TextHeadLarge(
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign? = TextAlign.Center,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Text(
        text = text,
        color = color,
        textAlign = textAlign,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineLarge,
        modifier = modifier
    )
}

@Composable
fun TextHeadMedium(
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign? = TextAlign.Center,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Text(
        text = text,
        color = color,
        textAlign = textAlign,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier
    )
}

@Composable
fun TextHeadSmall(
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign? = TextAlign.Center,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Text(
        text = text,
        color = color,
        textAlign = textAlign,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier
    )
}


@Composable
fun TextBodyLarge(
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign? = TextAlign.Unspecified,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        textAlign = textAlign,
        fontWeight = FontWeight.Normal,
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
    )
}

@Composable
fun TextBodyMedium(
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign? = TextAlign.Unspecified,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        textAlign = textAlign,
        fontWeight = FontWeight.Normal,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

@Composable
fun TextBodySmall(
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    textAlign: TextAlign? = TextAlign.Unspecified,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        color = color,
        textAlign = textAlign,
        fontWeight = FontWeight.Normal,
        style = MaterialTheme.typography.bodySmall,
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