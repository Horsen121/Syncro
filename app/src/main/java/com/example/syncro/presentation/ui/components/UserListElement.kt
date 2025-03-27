package com.example.syncro.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.syncro.R
import com.example.syncro.application.ui.theme.SyncroTheme
import com.example.syncro.data.models.User
import com.example.syncro.presentation.ui.elements.CheckTextButton
import com.example.syncro.presentation.ui.elements.TextBodyMedium
import com.example.syncro.presentation.ui.elements.TextHeadMedium

@Composable
fun UserListElement(
    user: User,
    isAdmin: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(10.dp, 0.dp)
            ) {
                TextHeadMedium(
                    text = user.name,
                    textAlign = TextAlign.Start,
                    fillMaxWidth = false
                )
                TextBodyMedium(
                    text = user.email,
                    textAlign = TextAlign.Start,
                )
            }
            if (isAdmin) {
                Spacer(Modifier.width(24.dp))

                Row {
                    CheckTextButton(
                        checked = user.isAdmin,
                        onCheckedChange = { onClick() },
                        text = stringResource(R.string.card_user),
                        leftTextPosition = true,
                        fillMaxWidth = false
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun GroupListElementPreview() {
    SyncroTheme {
        Scaffold { _ ->
            UserListElement(
                user = User(1, 1, "sdfa", "User", false),
                isAdmin = true,
                onClick = {  }
            )
        }
    }
}