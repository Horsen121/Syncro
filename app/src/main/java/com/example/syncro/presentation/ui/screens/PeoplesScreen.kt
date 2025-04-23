package com.example.syncro.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.syncro.R
import com.example.syncro.presentation.ui.components.TopBarText
import com.example.syncro.presentation.ui.components.UserListElement
import com.example.syncro.presentation.ui.elements.SimpleSearchBar
import com.example.syncro.presentation.viewmodels.PeoplesViewModel

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun PeoplesScreen(
    navController: NavController,
    viewModel: PeoplesViewModel = hiltViewModel()
) {
    val openInviteDialog = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarText(
            leftText = stringResource(R.string.back),
            centerText = stringResource(R.string.routing_group) + "\"${viewModel.getGroupName()}\"",
            rightText = stringResource(R.string.users_add),
            rightAction = { openInviteDialog.value = true },
            navController = navController
        ) }
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
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .defaultMinSize(Dp.Unspecified, 200.dp)
                    ) {
                        val searchState = remember { TextFieldState() }
                        SimpleSearchBar(
                            textFieldState = searchState,
                            onSearch = {
                                searchState.edit { replace(0, length, it) }
                                viewModel.search(it)
                            },
                            onClick = {
                                viewModel.invite(it)
                                openInviteDialog.value = false
                            },
                            searchResults = viewModel.search.value
                        )
                    }
                }
            }

            LazyColumn(
                horizontalAlignment = Alignment.Start,
                contentPadding = PaddingValues(10.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(viewModel.users.value) { user ->
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