package com.example.syncro.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.syncro.R
import com.example.syncro.presentation.ui.components.TopBarText
import com.example.syncro.presentation.ui.components.UserListElement
import com.example.syncro.presentation.ui.elements.TextBodyMedium
import com.example.syncro.presentation.viewmodels.PeoplesViewModel

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun PeoplesScreen(
    navController: NavController,
    viewModel: PeoplesViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarText(
            leftText = stringResource(R.string.back),
            centerText = stringResource(R.string.routing_group) + "\"${viewModel.getGroupName()}\"",
            rightText = stringResource(R.string.users_add),
            rightAction = { viewModel.invite() },
            navController = navController
        ) }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.End,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    0.dp,
                    paddingValues.calculateTopPadding(),
                    0.dp,
                    paddingValues.calculateBottomPadding()
                )
        ) {
            Button(
                onClick = { viewModel.add() }
            ) {
                TextBodyMedium("ADD_USER_FOR_TEST")
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