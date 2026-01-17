package com.example.syncro.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.syncro.R
import com.example.syncro.presentation.ui.components.TopBarText
import com.example.syncro.presentation.ui.components.UserListElement
import com.example.syncro.presentation.ui.elements.SimpleTextField
import com.example.syncro.presentation.ui.elements.TextBodyMedium
import com.example.syncro.presentation.ui.elements.TextHeadSmall
import com.example.syncro.presentation.viewmodels.PeoplesViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun PeoplesScreen(
    navController: NavController,
    viewModel: PeoplesViewModel = hiltViewModel()
) {
    val openInviteDialog = remember { mutableStateOf(false) }

    var refreshing by remember { mutableStateOf(false) }
    LaunchedEffect(refreshing) {
        if (refreshing) {
            viewModel.update()
            delay(1000)
            refreshing = false
        }
    }

    val users = viewModel.users.collectAsState()
    val search = viewModel.search.collectAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = { refreshing = true },
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopBarText(
                    leftText = stringResource(R.string.back),
                    centerText = stringResource(R.string.routing_group) + "\"${viewModel.getGroupName()}\"",
                    rightText = stringResource(R.string.users_add),
                    rightAction = { openInviteDialog.value = true },
                    navController = navController
                )
            }
        ) { paddingValues ->
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        0.dp,
                        paddingValues.calculateTopPadding() + 10.dp,
                        0.dp,
                        paddingValues.calculateBottomPadding()
                    )
            ) {
                if (openInviteDialog.value) {
                    Dialog(
                        onDismissRequest = { openInviteDialog.value = false }
                    ) {
                        Surface {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
//                                    .defaultMinSize(Dp.Unspecified, 100.dp)
                            ) {
                                SimpleTextField(
                                    value = search.value,
                                    placeholder = { TextBodyMedium(stringResource(R.string.users_add_placeholder)) },
                                    onValueChange = { viewModel.search(it) },
                                    maxLength = 30
                                )
                                Button(
                                    onClick = {
                                        viewModel.invite(viewModel.search.value)
                                        openInviteDialog.value = false
                                    }
                                ) {
                                    TextHeadSmall(stringResource(R.string.users_add))
                                }
                            }
//                            SimpleSearchBar(
//                                textFieldState = searchState,
//                                onSearch = {
//                                    searchState.edit { replace(0, length, it) }
//                                    viewModel.search(it)
//                                },
//                                onClick = {
//                                    viewModel.invite(it)
//                                    openInviteDialog.value = false
//                                },
//                                searchResults = viewModel.search.value
//                            )
                        }
                    }
                }

                LazyColumn(
                    horizontalAlignment = Alignment.Start,
                    contentPadding = PaddingValues(10.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(users.value) { user ->
                        UserListElement(
                            user = user,
                            isAdmin = viewModel.getIsAdmin(),
                            onClick = { viewModel.changeIsAdmin(user.user_id!!) }
                        )
                        Spacer(Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}