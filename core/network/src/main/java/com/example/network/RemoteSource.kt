package com.example.network

import com.example.network.dto.AddMemberRequest
import com.example.network.dto.CreateGroupRequest
import com.example.network.dto.CreateGroupResponse
import com.example.network.dto.CreateSolutionRequest
import com.example.network.dto.CreateTaskRequest
import com.example.network.dto.CurrentUser
import com.example.network.dto.File
import com.example.network.dto.FindGroupResponse
import com.example.network.dto.Group
import com.example.network.dto.JoinGroupRequest
import com.example.network.dto.JoinGroupResponse
import com.example.network.dto.LeaveGroupResponse
import com.example.network.dto.LoginRequest
import com.example.network.dto.RegisterRequest
import com.example.network.dto.Solution
import com.example.network.dto.SourceFile
import com.example.network.dto.Task
import com.example.network.dto.UpdSolutionRequest
import com.example.network.dto.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthApi {

    @POST("auth/login-pwd")
    suspend fun login(@Body body: LoginRequest): Response<CurrentUser>

    @POST("auth/register")
    suspend fun register(@Body body: RegisterRequest): Response<CurrentUser>
}

interface RemoteApiService {
    // Groups
    @PUT("public/groups/{groupId}/join")
    suspend fun joinGroup(
        @Header("Authorization:Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Body body: JoinGroupRequest,
    ): Response<JoinGroupResponse>

    @PUT("/api/groups/{group_id}/leave")
    suspend fun disJoinGroup(
        @Header("Authorization:Bearer") token: String,
        @Path("group_id") groupId: Long,
    ): Response<LeaveGroupResponse>

    @GET("api/groups/search")
    suspend fun findGroup(
        @Header("Authorization:Bearer") token: String,
        @Query("query") group: String,
    ): Response<List<FindGroupResponse>>

    @POST("api/groups")
    suspend fun createGroup(
        @Header("Authorization:Bearer") token: String,
        @Body body: CreateGroupRequest,
    ): Response<CreateGroupResponse>

    @PUT("api/groups/{group_id}")
    suspend fun updGroupById(
        @Header("Authorization:Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Body body: CreateGroupRequest,
    ): Response<Group>

    @GET("api/groups")
    suspend fun getGroupsByUser(
        @Header("Authorization:Bearer") token: String
    ): Response<List<Group>>

    @GET("api/groups/{group_id}")
    suspend fun getGroup(
        @Header("Authorization:Bearer") token: String,
        @Path("group_id") groupId: Long,
    ): Response<Group>

    @GET("api/groups/{group_id}/members")
    suspend fun getMembersOfGroup(
        @Header("Authorization:Bearer") token: String,
        @Path("group_id") groupId: Long,
    ): Response<List<User>>

    @POST("api/groups/{group_id}/members")
    suspend fun addMemberToGroup(
        @Header("Authorization:Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Body body: AddMemberRequest,
    ): Response<JoinGroupResponse> // User

    @PUT("api/groups/{group_id}/members/{user_id}")
    suspend fun addAdminToGroup(
        @Header("Authorization:Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Path("user_id") userId: Long,
    ): Response<String>

    @DELETE("api/groups/{group_id}/members/{user_id}")
    suspend fun deleteAdminOfGroup(
        @Header("Authorization:Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Path("user_id") userId: Long,
    ): Response<String>

    // Tasks
    @POST("api/groups/{group_id}/tasks")
    suspend fun addTaskToGroup(
        @Header("Authorization:Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Body body: CreateTaskRequest,
    ): Response<Task>

    @PUT("api/groups/{group_id}/tasks/{task_id}")
    suspend fun updTaskById(
        @Header("Authorization:Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Path("task_id") taskId: Long,
        @Body body: CreateTaskRequest,
    ): Response<Task>

    @GET("api/groups/{group_id}/tasks")
    suspend fun getTasksByGroup(
        @Header("Authorization:Bearer") token: String,
        @Path("group_id") groupId: Long,
    ): Response<List<Task>>

    @GET("api/groups/{group_id}/tasks/{task_id}")
    suspend fun getTaskById(
        @Header("Authorization:Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Path("task_id") taskId: Long,
    ): Response<Task>

    @GET("")
    suspend fun getFilesByTask(
        @Header("Authorization:Bearer") token: String
    ): Response<List<File>>

    @POST("")
    suspend fun addTaskFiles(
        @Header("Authorization:Bearer") token: String
    ): Response<Unit>

    // Solutions
    @GET("api/groups/{group_id}/tasks/{task_id}/solutions")
    suspend fun getSolutionsByTask(
        @Header("Authorization:Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Path("task_id") taskId: Long,
    ): Response<List<Solution>>

    @GET("api/groups/{group_id}/tasks/{task_id}/solutions/{solution_id}")
    suspend fun getSolutionById(
        @Header("Authorization:Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Path("task_id") taskId: Long,
        @Path("solution_id") solutionId: Long,
    ): Response<Solution>

    @POST("api/groups/{group_id}/tasks/{task_id}/solutions")
    suspend fun addSolutionToTask(
        @Header("Authorization:Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Path("task_id") taskId: Long,
        @Body body: CreateSolutionRequest,
    ): Response<Solution>

    @PUT("api/groups/{group_id}/tasks/{task_id}/solutions/{solution_id}")
    suspend fun updSolutionById(
        @Header("Authorization:Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Path("task_id") taskId: Long,
        @Path("solution_id") solutionId: Long,
        @Body body: UpdSolutionRequest,
    ): Response<Solution>

    @GET("")
    suspend fun getSourcesBySolution(
        @Header("Authorization:Bearer") token: String
    ): Response<List<SourceFile>>

    @POST("")
    suspend fun addSolutionSources(
        @Header("Authorization:Bearer") token: String
    ): Response<Unit>
}