package com.example.syncro.presentation.ui.screens.group

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.syncro.R
import com.example.syncro.application.Routing
import com.example.syncro.presentation.ui.components.GroupTask
import com.example.syncro.presentation.ui.components.SimpleBottomBar
import com.example.syncro.presentation.ui.components.TopBarBackButton
import com.example.syncro.presentation.ui.elements.TextBodySmall
import com.example.syncro.presentation.viewmodels.group.GroupViewModel
import com.example.syncro.presentation.viewmodels.group.GroupViewModel.GroupData

@Composable
fun GroupScreen(
    navController: NavController,
    viewModel: GroupViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarBackButton(text = stringResource(R.string.group_title) + " \"${viewModel.group.value?.name}\"", navController) },
        bottomBar = {
            val actions = mutableListOf(
                Triple(
                    stringResource(R.string.group_menu_bottom_chat),
                    { navController.navigate(Routing.GroupChatScreen.route + "?groupId=${viewModel.group.value?.group_id}") },
                    Icons.Default.Email
                ),
                Triple(
                    stringResource(R.string.group_menu_bottom_notifications),
                    { navController.navigate(Routing.RemindersScreen.route + "?groupId=${viewModel.group.value?.group_id}") },
                    Icons.Default.Notifications
                ),
                Triple(
                    stringResource(R.string.group_menu_bottom_people),
                    { navController.navigate(Routing.PeoplesScreen.route
                            + "?groupId=${viewModel.group.value?.group_id}&groupName=${viewModel.group.value?.name}&isAdmin=${viewModel.group.value?.isAdmin}") },
                    Icons.Default.Person
                )
            )
            if(viewModel.group.value?.isAdmin == true)
                actions.add(
                    Triple(
                        stringResource(R.string.group_menu_bottom_settings),
                        { navController.navigate(Routing.AddEditGroupScreen.route + "?groupId=${viewModel.group.value?.group_id}") },
                        Icons.Default.Build
                    )
                )
            SimpleBottomBar(actions)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Routing.TaskScreen.route + "?groupId=${viewModel.group.value?.group_id}") }
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
                    paddingValues.calculateTopPadding(),
                    0.dp,
                    paddingValues.calculateBottomPadding()
                )
        ) {
            NavigationBar(
                modifier = Modifier
                    .padding(10.dp)
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .clip(MaterialTheme.shapes.large)
            ) {
                NavigationBarItem(
                    selected = viewModel.currentTopNavBar.value == GroupData.Current,
                    onClick = { viewModel.getData(GroupData.Current) },
                    label = { TextBodySmall(stringResource(R.string.group_menu_top_current)) },
                    icon = { Image(Icons.Default.Notifications, null) }
                )
                NavigationBarItem(
                    selected = viewModel.currentTopNavBar.value == GroupData.Plan,
                    onClick = { viewModel.getData(GroupData.Plan) },
                    label = { TextBodySmall(stringResource(R.string.group_menu_top_plan)) },
                    icon = { Image(Icons.Default.DateRange, null) }
                )
                NavigationBarItem(
                    selected = viewModel.currentTopNavBar.value == GroupData.Done,
                    onClick = { viewModel.getData(GroupData.Done) },
                    label = { TextBodySmall(stringResource(R.string.group_menu_top_done)) },
                    icon = { Image(Icons.Default.Check, null) }
                )
            }

            LazyColumn(
                contentPadding = PaddingValues(top = 10.dp),
                modifier = Modifier
                    .fillMaxSize(0.9f)
                    .offset(y = -WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding())
            ) {
                items(viewModel.currentData.value) {task ->
                    GroupTask(
                        task = task,
                        onClick = { navController.navigate(Routing.TaskScreen.route + "?groupId=${viewModel.group.value?.group_id}&taskId=${task.task_id}") },
                        onSolutionClick = { navController.navigate(Routing.SolutionsScreen.route + "?groupId=${viewModel.group.value?.group_id}&taskId=${task.task_id}") }
                    )
                    Spacer(Modifier.height(10.dp))
                }
            }
        }
    }
}