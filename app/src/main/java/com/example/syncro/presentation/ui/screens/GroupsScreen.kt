package com.example.syncro.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.syncro.R
import com.example.syncro.application.Routing
import com.example.syncro.presentation.ui.components.GroupListElement
import com.example.syncro.presentation.ui.components.SimpleBottomBar
import com.example.syncro.presentation.ui.components.TopBarSimple
import com.example.syncro.presentation.ui.elements.SimpleSearchBar
import com.example.syncro.presentation.viewmodels.GroupsViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun GroupsScreen(
    navController: NavController,
    viewModel: GroupsViewModel = hiltViewModel()
) {

    var refreshing by remember { mutableStateOf(false) }
    LaunchedEffect(refreshing) {
        if (refreshing) {
            viewModel.update()
            delay(1000)
            refreshing = false
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = { refreshing = true },
        modifier = Modifier.fillMaxSize()
    ) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarSimple(text = stringResource(R.string.routing_groups)) },
        bottomBar = {
            val actions = mutableListOf(
                Triple(
                    stringResource(R.string.group_menu_bottom_notifications),
                    { navController.navigate(Routing.RemindersScreen.route) },
                    Icons.Default.Notifications
                ),
                Triple(
                    stringResource(R.string.group_menu_bottom_settings),
                    { navController.navigate(Routing.SettingsScreen.route) },
                    Icons.Default.AccountCircle
                )
            )
            SimpleBottomBar(actions)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Routing.AddEditGroupScreen.route) }
            ) {
                Image(Icons.Default.Add, null)
            }
        }
    ) { paddingValues ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        0.dp,
                        paddingValues.calculateTopPadding() + 10.dp,
                        0.dp,
                        paddingValues.calculateBottomPadding()
                    )
            ) {
                val searchState = remember { TextFieldState() }
                SimpleSearchBar(
                    textFieldState = searchState,
                    onSearch = {
                        searchState.edit { replace(0, length, it) }
                        viewModel.search(it)
                    },
                    onClick = {
                        navController.navigate(Routing.GroupScreen.route + "?groupId=${it.toLong()}")
                    },
                    searchResults = viewModel.search.value
                )

                LazyColumn(
                    contentPadding = PaddingValues(10.dp)
                ) {
                    items(viewModel.groups.value) { group ->
                        GroupListElement(
                            group = group,
                            onClick = { navController.navigate(Routing.GroupScreen.route + "?groupId=${group.group_id}") }
                        )
                        Spacer(Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}