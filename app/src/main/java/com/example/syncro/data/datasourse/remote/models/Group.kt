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
    val is_admin: Boolean = false,
    val skip_invitation: Boolean = true
)

data class FindGroupResponse(
    val name: String,
    val group_id: Long
)

data class LeaveGroupResponse(
    val group_id: Long,
    val message: String
)

data class JoinGroupRequest(
    val email: String,
    val full_name: String,
    val password: String
)

data class JoinGroupResponse(
    val message: String
)