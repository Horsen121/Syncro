package com.example.syncro.presentation.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.syncro.application.ui.theme.SyncroTheme

@Composable
fun SimpleTextField( // TODO: hide keyboard
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    placeholder: @Composable (() -> Unit)?,
    isError: Boolean = false,
    maxLength: Int = Int.MAX_VALUE
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            if (it.length <= maxLength)
                onValueChange(it)
        },
        placeholder = placeholder,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, capitalization = KeyboardCapitalization.Sentences),
        shape = MaterialTheme.shapes.medium,
        isError = isError,
        supportingText = {
            if (maxLength != Int.MAX_VALUE)
                "${value.length} / $maxLength"
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedBorderColor = if (value.length <= maxLength) MaterialTheme.colorScheme.primary else Color.Red,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = modifier.then(modifier.fillMaxWidth())
    )
}

@Composable
fun MultiLineTextField(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    placeholder: @Composable (() -> Unit)?,
    isError: Boolean = false,
    minLines: Int = 4,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        textStyle = LocalTextStyle.current.copy(textIndent = TextIndent(25.sp)),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            showKeyboardOnFocus = true
        ),
        shape = MaterialTheme.shapes.medium,
        isError = isError,
        minLines = minLines,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = modifier.then(modifier.fillMaxWidth())
    )
}

@Composable
fun PasswordTextField(
    value: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    placeholder: @Composable (() -> Unit)?,
    isError: Boolean = false,
) {
    val mod = remember { mutableStateOf(true) }
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = placeholder,
        keyboardOptions = KeyboardOptions(keyboardType = if (mod.value) KeyboardType.Password else KeyboardType.Text),
        trailingIcon = {
            Icon(
                imageVector = if (mod.value) Icons.Default.Lock else Icons.Default.Warning,
                contentDescription = null,
                modifier = Modifier.clickable { mod.value = !mod.value }
            ) }
        ,
        shape = MaterialTheme.shapes.medium,
        isError = isError,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedTrailingIconColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.onBackground,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = modifier.then(modifier.fillMaxWidth())
    )
}


@Preview(showBackground = true)
@Composable
fun TextFieldPreview() {
    SyncroTheme {
        Column {
            val text = remember { mutableStateOf("") }
            SimpleTextField(
                value = text.value,
                onValueChange = { text.value = it },
                placeholder = { Text("sdfsdf")},
                maxLength = 50
            )
            Spacer(modifier = Modifier.height(10.dp))
            PasswordTextField(value = text.value, onValueChange = { text.value = it }, placeholder = { Text(
                text = "sdfsdf"
            )})
            Spacer(modifier = Modifier.height(10.dp))
            MultiLineTextField(value = text.value, onValueChange = { text.value = it }, placeholder = { Text(
                text = "sdfsdf dfs fsg sdf \ng fsdg sg dfsg"
            )})
        }
    }
}