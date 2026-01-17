package com.example.syncro.data.datasourse.remote.models

data class CreateGroupRequest(
    val name: String,
    val description: String
)
data class CreateGroupResponse(
    val groupId: Long,
    val name: String,
    val description: String,
    val isAdmin: Boolean
)

data class AddMemberRequest(
    val email: String,
    val isAdmin: Boolean = false,
    val skipInvitation: Boolean = true
)

data class FindGroupResponse(
    val name: String,
    val groupId: Long
)

data class LeaveGroupResponse(
    val groupId: Long,
    val message: String
)

data class JoinGroupRequest(
    val email: String,
    val fullName: String
)

data class JoinGroupResponse(
    val message: String
)