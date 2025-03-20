package com.example.syncro.domain.usecases

import com.example.syncro.domain.usecases.group.AddGroup
import com.example.syncro.domain.usecases.group.DeleteGroup
import com.example.syncro.domain.usecases.group.GetGroup
import com.example.syncro.domain.usecases.group.GetGroups

data class GroupUseCases(
    val getGroups: GetGroups,
    val deleteGroup: DeleteGroup,
    val addGroup: AddGroup,
    val getGroup: GetGroup,
)