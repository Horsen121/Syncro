package com.example.syncro.presentation.ui.screens.group

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.syncro.application.ui.theme.SyncroTheme
import com.example.syncro.presentation.viewmodels.group.GroupChatViewModel

@Composable
fun GroupChatScreen(
    navController: NavController,

) {
    val viewModel = GroupChatViewModel()
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 0.dp, 0.dp, paddingValues.calculateBottomPadding())
        ) {

        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GroupChatScreenPreview() {
    SyncroTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) {
            GroupChatScreen(
                rememberNavController(),
            )
        }
    }
}