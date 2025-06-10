package com.example.syncro.data.datasourse.remote

import com.example.syncro.data.datasourse.remote.models.AddMemberRequest
import com.example.syncro.data.datasourse.remote.models.CreateGroupRequest
import com.example.syncro.data.datasourse.remote.models.CreateGroupResponse
import com.example.syncro.data.datasourse.remote.models.CreateSolutionRequest
import com.example.syncro.data.datasourse.remote.models.CreateTaskRequest
import com.example.syncro.data.datasourse.remote.models.FindGroupResponse
import com.example.syncro.data.datasourse.remote.models.LoginRequest
import com.example.syncro.data.datasourse.remote.models.LoginResponse
import com.example.syncro.data.datasourse.remote.models.RegisterRequest
import com.example.syncro.data.datasourse.remote.models.RegisterResponse
import com.example.syncro.data.datasourse.remote.models.UpdSolutionRequest
import com.example.syncro.data.models.File
import com.example.syncro.data.models.Group
import com.example.syncro.data.models.Solution
import com.example.syncro.data.models.SourceFile
import com.example.syncro.data.models.Task
import com.example.syncro.data.models.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

private const val BASE_URL = "http://194.87.250.206:3000/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface RemoteApiService {
    // Auth
    @POST("auth/register")
    suspend fun register(@Body body: RegisterRequest): Response<RegisterResponse>

    @POST("auth/login-pwd")
    suspend fun login(@Body body: LoginRequest): Response<LoginResponse>

    // Groups
    @PUT("")
    suspend fun joinGroup(
        @Header("Authorization: Bearer") token: String,
        groupId: Long
    ): Boolean

    @PUT("")
    suspend fun disJoinGroup(
        @Header("Authorization: Bearer") token: String,
        groupId: Long
    ): Boolean

    @GET("")
    suspend fun findGroup(
        @Header("Authorization: Bearer") token: String,
        group: String
    ): Response<List<FindGroupResponse>>

    @POST("api/groups")
    suspend fun createGroup(
        @Header("Authorization: Bearer") token: String,
        @Body body: CreateGroupRequest
    ): Response<CreateGroupResponse>

    @PUT("api/groups/{group_id}")
    suspend fun updGroupById(
        @Header("Authorization: Bearer") token: String,
        @Body body: CreateGroupRequest
    ): Response<Group>

    @GET("api/groups")
    suspend fun getGroupsByUser(
        @Header("Authorization: Bearer") token: String
    ): Response<List<Group>>

    @GET("api/groups/{group_id}")
    suspend fun getGroup(
        @Header("Authorization: Bearer") token: String,
        @Path("group_id") groupId: Long
    ): Response<Group>

    @GET("api/groups/{group_id}/members")
    suspend fun getMembersOfGroup(
        @Header("Authorization: Bearer") token: String,
        @Path("group_id") groupId: Long
    ): Response<List<User>>

    @POST("api/groups/{group_id}/members")
    suspend fun addMemberToGroup(
        @Header("Authorization: Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Body body: AddMemberRequest
    ): Response<User>

    @PUT("api/groups/{group_id}/members/{user_id}")
    suspend fun addAdminToGroup(
        @Header("Authorization: Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Path("user_id") user_id: Long
    ): Response<String>

    @DELETE("api/groups/{group_id}/members/{user_id}")
    suspend fun deleteAdminOfGroup(
        @Header("Authorization: Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Path("user_id") user_id: Long
    ): Response<String>

    // Tasks
    @POST("api/groups/{group_id}/tasks")
    suspend fun addTaskToGroup(
        @Header("Authorization: Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Body body: CreateTaskRequest
    ): Response<Task>

    @PUT("api/groups/{groupID}/tasks/{taskID}")
    suspend fun updTaskById(
        @Header("Authorization: Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Body body: CreateTaskRequest
    ): Response<Task>

    @GET("api/groups/{group_id}/tasks")
    suspend fun getTasksByGroup(
        @Header("Authorization: Bearer") token: String,
        @Path("group_id") groupId: Long
    ): Response<List<Task>>

    @GET("api/groups/{group_id}/tasks/{task_id}")
    suspend fun getTaskById(
        @Header("Authorization: Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Path("task_id") task_id: Long
    ): Response<Task>

    @GET("")
    suspend fun getFilesByTask(

    ): List<File>

    @POST("")
    suspend fun addTaskFiles(

    ): Boolean

    // Solutions
    @GET("api/groups/{group_id}/tasks/{task_id}/solutions")
    suspend fun getSolutionsByTask(
        @Header("Authorization: Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Path("task_id") taskId: Long
    ): Response<List<Solution>>

    @GET("api/groups/{group_id}/tasks/{task_id}/solutions/{solution_id}")
    suspend fun getSolutionById(
        @Header("Authorization: Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Path("task_id") taskId: Long,
        @Path("solution_id") solution_id: Long
    ): Response<Solution>

    @POST("api/groups/{group_id}/tasks/{task_id}/solutions")
    suspend fun addSolutionToTask(
        @Header("Authorization: Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Path("task_id") taskId: Long,
        @Body body: CreateSolutionRequest
    ): Response<Solution>

    @PUT("api/groups/{group_id}/tasks/{task_id}/solutions/{solution_id}")
    suspend fun updSolutionById(
        @Header("Authorization: Bearer") token: String,
        @Path("group_id") groupId: Long,
        @Path("task_id") taskId: Long,
        @Path("solution_id") solution_id: Long,
        @Body body: UpdSolutionRequest
    ): Response<Solution>

    @GET("")
    suspend fun getSourcesBySolution(

    ): List<SourceFile>

    @POST("")
    suspend fun addSolutionSources(

    ): Boolean
}

object RemoteApi {
    val retrofitService : RemoteApiService by lazy {
        retrofit.create(RemoteApiService::class.java)
    }
}