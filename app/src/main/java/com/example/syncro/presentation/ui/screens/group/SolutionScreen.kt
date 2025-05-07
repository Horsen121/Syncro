package com.example.syncro.presentation.ui.screens.group

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.syncro.R
import com.example.syncro.application.Routing
import com.example.syncro.presentation.ui.components.FileCard
import com.example.syncro.presentation.ui.components.TopBarText
import com.example.syncro.presentation.ui.elements.TextHeadSmall
import com.example.syncro.presentation.viewmodels.group.SolutionViewModel

@Composable
fun SolutionScreen(
    navController: NavController,
    viewModel: SolutionViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarText(
            leftText = stringResource(R.string.back),
            centerText = stringResource(R.string.solution_title),
            rightText = stringResource(R.string.edit),
            navController = navController,
            rightAction = { navController.navigate(
                Routing.AddEditSolutionScreen.route +
                    "?groupId=${viewModel.groupId}&taskId=${viewModel.taskId}&solutionId=${viewModel.solutionId}") }
        ) }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    10.dp,
                    paddingValues.calculateTopPadding(),
                    10.dp,
                    paddingValues.calculateBottomPadding()
                )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
            ) {
                TextHeadSmall(
                    text = stringResource(R.string.solution_title_title)
                )
                TextHeadSmall(
                    text = viewModel.name.value,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(10.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))

                TextHeadSmall(
                    text = stringResource(R.string.solution_description_title)
                )
                TextHeadSmall(
                    text = viewModel.desc.value,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(10.dp, 0.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextHeadSmall(text = stringResource(R.string.solution_files))
                }

                LazyColumn {
                    items(viewModel.sources.value) { source ->
                        FileCard(
                            source,
                            onClick = {  }
                        )
                    }
                }
            }
        }
    }
}