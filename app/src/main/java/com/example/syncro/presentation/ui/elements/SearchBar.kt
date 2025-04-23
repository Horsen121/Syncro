package com.example.syncro.presentation.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleSearchBar(
    textFieldState: TextFieldState,
    onSearch: (String) -> Unit,
    searchResults: List<Pair<String,String>>,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // Controls expansion state of the search bar
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier
            .fillMaxWidth()
            .semantics { isTraversalGroup = true }
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = 0f },
            inputField = {
                SearchBarDefaults.InputField(
                    query = textFieldState.text.toString(),
                    onQueryChange = {
                        textFieldState.edit { replace(0, length, it) }
//                        onSearch(textFieldState.text.toString())
                    },
                    onSearch = {
                        onSearch(textFieldState.text.toString())
                        textFieldState.edit { replace(0, length, "") }
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { TextBodyMedium("Search") },
                    leadingIcon = { Icon(Icons.Default.Search, null) },
                    trailingIcon = {
                        if (expanded) {
                            Icon(
                                Icons.Default.Clear, null,
                                modifier = Modifier.clickable { textFieldState.edit { replace(0, length, "") } }
                            )
                        }
                    }
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            // Display search results in a scrollable column
            Column(Modifier.verticalScroll(rememberScrollState())) {
                searchResults.forEach { result ->
                    ListItem(
                        headlineContent = { TextBodyLarge(result.first) },
                        supportingContent = { TextBodyMedium(result.second) },
                        modifier = Modifier
                            .clickable {
                                textFieldState.edit { replace(0, length, result.first) }
                                onClick(result.second)
                                expanded = false
                            }
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}