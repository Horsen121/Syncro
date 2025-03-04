package com.example.syncro.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.syncro.R
import com.example.syncro.application.Routing
import com.example.syncro.application.ui.theme.SyncroTheme
import com.example.syncro.presentation.ui.components.GroupListElement
import com.example.syncro.presentation.ui.components.TopBarSimple
import com.example.syncro.presentation.viewmodels.GroupsViewModel

@Composable
fun GroupsScreen(
    navController: NavController,
) {
    val viewModel = GroupsViewModel()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarSimple(text = stringResource(R.string.routing_groups)) }
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
            LazyColumn {
                items(viewModel.groups.value) { group ->
                    GroupListElement(
                        group = group,
                        onClick = { navController.navigate(Routing.GroupScreen.route+ "id= ${group.id}") }
                    )
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
fun GroupsScreenPreview() {
    SyncroTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            GroupsScreen(
                rememberNavController()
            )
        }
    }
}