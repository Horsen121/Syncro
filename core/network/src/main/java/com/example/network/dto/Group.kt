package com.example.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val groupId : Long? = null,
    val name: String,
    val description: String,
    val membersCount: Int,
    val createdBy: Long,
    val isAdmin: Boolean,
    val isMember: Boolean = true
)


@Serializable
data class CreateGroupRequest(
    val name: String,
    val description: String
)

@Serializable
data class CreateGroupResponse(
    val groupId: Long,
    val name: String,
    val description: String,
    val isAdmin: Boolean
)

@Serializable
data class AddMemberRequest(
    val email: String,
    val isAdmin: Boolean = false,
    val skipInvitation: Boolean = true
)

@Serializable
data class FindGroupResponse(
    val name: String,
    val groupId: Long
)

@Serializable
data class LeaveGroupResponse(
    val groupId: Long,
    val message: String
)

@Serializable
data class JoinGroupRequest(
    val email: String,
    val fullName: String
)

@Serializable
data class JoinGroupResponse(
    val message: String
)