package com.example.syncro.presentation.ui.models.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.syncro.application.ui.theme.SyncroTheme

@Composable
fun SimpleTextField(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    placeholder: @Composable (() -> Unit)?,
    isError: Boolean = false,
    fillMaxWidth: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        shape = MaterialTheme.shapes.medium,
        isError = isError,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = if(fillMaxWidth) modifier.fillMaxWidth() else modifier
    )
}

@Composable
fun PasswordTextField(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    placeholder: @Composable (() -> Unit)?,
    isError: Boolean = false,
    fillMaxWidth: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = null) }, // TODO change Icon
        shape = MaterialTheme.shapes.medium,
        isError = isError,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = if(fillMaxWidth) modifier.fillMaxWidth() else modifier
    )
}


@Preview(showBackground = true)
@Composable
fun TextFieldPreview() {
    SyncroTheme {
        Column {
            val text = remember { mutableStateOf("text") }
            SimpleTextField(value = text.value, onValueChange = { text.value = it }, placeholder = { Text(
                text = "sdfsdf"
            )})
            Spacer(modifier = Modifier.height(10.dp))
            PasswordTextField(value = text.value, onValueChange = { text.value = it }, placeholder = { Text(
                text = "sdfsdf"
            )})
        }
    }
}