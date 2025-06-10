package com.example.syncro.data.datasourse.remote.models

data class CreateGroupRequest(
    val name: String,
    val description: String
)
data class CreateGroupResponse(
    val group_id: Long,
    val name: String,
    val description: String,
    val is_admin: Boolean
)

data class AddMemberRequest(
    val email: String,
    val is_admin: Boolean
)

data class FindGroupResponse(
    val name: String,
    val id: Long
)