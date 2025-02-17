package com.example.syncro.presentation.ui.screens.group

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.syncro.R
import com.example.syncro.application.Routing
import com.example.syncro.application.ui.theme.SyncroTheme
import com.example.syncro.presentation.ui.components.GroupTask
import com.example.syncro.presentation.ui.components.SimpleBottomBar
import com.example.syncro.presentation.ui.components.SimpleTopBar
import com.example.syncro.presentation.ui.elements.TextBodySmall
import com.example.syncro.presentation.viewmodels.group.GroupViewModel
import com.example.syncro.presentation.viewmodels.group.GroupViewModel.GroupData

@Composable
fun GroupScreen(
    navController: NavController,
    groupName: String,
) {
    val viewModel = GroupViewModel()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { SimpleTopBar(text = stringResource(R.string.group_title) + " \"${groupName}\"") },
        bottomBar = {
            SimpleBottomBar(listOf(
                Triple(
                    stringResource(R.string.group_menu_bottom_chat),
                    {navController.navigate(Routing.GroupChatScreen)},
                    Icons.Default.Email
                    ),
                Triple(
                    stringResource(R.string.group_menu_bottom_people),
                    { },
                    Icons.Default.Email
                )
            ))
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    0.dp,
                    paddingValues.calculateTopPadding(),
                    0.dp,
                    paddingValues.calculateBottomPadding()
                )
        ) {
            NavigationBar( // TODO: change to beautiful
                modifier = Modifier
                    .padding(10.dp)
                    .clip(MaterialTheme.shapes.large)
            ) {
                NavigationBarItem(
                    selected = viewModel.currentTopNavBar.value == GroupData.Current,
                    onClick = { viewModel.getData(GroupData.Current) },
                    label = { TextBodySmall(stringResource(R.string.group_menu_top_current)) },
                    icon = {}
                )
                NavigationBarItem(
                    selected = viewModel.currentTopNavBar.value == GroupData.Plan,
                    onClick = { viewModel.getData(GroupData.Plan) },
                    label = { TextBodySmall(stringResource(R.string.group_menu_top_plan)) },
                    icon = {}
                )
                NavigationBarItem(
                    selected = viewModel.currentTopNavBar.value == GroupData.Done,
                    onClick = { viewModel.getData(GroupData.Done) },
                    label = { TextBodySmall(stringResource(R.string.group_menu_top_done)) },
                    icon = {}
                )
            }

            LazyColumn {
                items(viewModel.currentData.value) {elem ->
                    GroupTask() // TODO: add parametrs
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
//    showSystemUi = true,
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GroupScreenPreview() {
    SyncroTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            GroupScreen(
                rememberNavController(),
                "Example"
            )
        }
    }
}