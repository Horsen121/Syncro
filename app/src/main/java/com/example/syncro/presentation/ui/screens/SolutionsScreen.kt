package com.example.syncro.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.syncro.R
import com.example.syncro.application.Routing
import com.example.syncro.presentation.ui.components.SolutionListElement
import com.example.syncro.presentation.ui.components.TopBarBackButton
import com.example.syncro.presentation.viewmodels.SolutionsViewModel

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun SolutionsScreen(
    navController: NavController,
    viewModel: SolutionsViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarBackButton(text = stringResource(R.string.routing_solutions), navController) }
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
            LazyColumn(
                contentPadding = PaddingValues(10.dp)
            ) {
                items(viewModel.solutions.value) { solution ->
                    SolutionListElement(
                        solution = solution,
                        onClick = { navController.navigate(Routing.SolutionScreen.route
                                + "?groupId=${viewModel.getGroupId()}&taskId=${viewModel.getTaskId()}&solutionId=${solution.solution_id}") }
                    )
                    Spacer(Modifier.height(10.dp))
                }
            }
        }
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 8.dp, bottom = 64.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            FloatingActionButton(
                onClick = { navController.navigate(Routing.AddEditSolutionScreen.route +
                        "?groupId=${viewModel.getGroupId()}&taskId=${viewModel.getTaskId()}")
                }
            ) {
                Image(Icons.Default.Add, null)
            }
        }
    }
}