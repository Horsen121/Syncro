package com.example.syncro.data.datasourse.remote

import com.example.syncro.application.CurrentUser
import com.example.syncro.data.models.File
import com.example.syncro.data.models.Group
import com.example.syncro.data.models.Solution
import com.example.syncro.data.models.SourceFile
import com.example.syncro.data.models.Task
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

private const val BASE_URL = "http://85.92.108.147:3000/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

//"email": "user1@example.com",
//"full_name": "Test User 1",
//"password": "password123"

interface RemoteApiService {
    @FormUrlEncoded
    @POST("auth/register")// /email={email}&full_name={full_name}&password={password}")
    suspend fun register(@Field("email") email: String, @Field("full_name") name: String, @Field("password") password: String): String

    @FormUrlEncoded
//    @Headers("Content-Type: application/json")
    @POST("auth/login-pwd") // /email={email}&password={password}
    suspend fun login(@Field("email") email: String, @Field("password") password: String): String
//    suspend fun login(@Body body: String): String

    @PUT("")
    suspend fun joinGroup(groupId: Long, token: String = CurrentUser.token): Boolean

    @PUT("")
    suspend fun disJoinGroup(groupId: Long, token: String = CurrentUser.token): Boolean

    @GET("")
    suspend fun getGroupsByUser(token: String = CurrentUser.token): List<Group>

    @GET("")
    suspend fun getTasksByGroup(groupId: Long, token: String = CurrentUser.token): List<Task>

    @GET("")
    suspend fun getFilesByTask(groupId: Long, taskId: Long, token: String = CurrentUser.token): List<File>

    @GET("")
    suspend fun getSolutionsByTask(groupId: Long, taskId: Long, token: String = CurrentUser.token): List<Solution>

    @GET("")
    suspend fun getSourcesBySolution(groupId: Long, taskId: Long, solId: Long, token: String = CurrentUser.token): List<SourceFile>

    @POST("")
    suspend fun createGroup(name: String, description: String, isPrivate: Boolean, token: String = CurrentUser.token): Boolean

    @POST("")
    suspend fun createTask(groupId: Long, title: String, description: String, start: String, end: String, difficult: Byte, reminder: String, token: String = CurrentUser.token): Long

    @POST("")
    suspend fun createSolution(groupId: Long, taskId: Long, title: String, description: String, token: String = CurrentUser.token): Long

    @POST("")
    suspend fun addTaskFiles(groupId: Long, taskId: Long, files: List<File>, token: String = CurrentUser.token): Boolean

    @POST("")
    suspend fun addSolutionSources(groupId: Long, taskId: Long, solId: Long, sources: List<SourceFile>, token: String = CurrentUser.token): Boolean

    @PUT("")
    suspend fun changeGroup(groupId: Long, name: String, description: String, isPrivate: Boolean, token: String = CurrentUser.token): Boolean

    @PUT("")
    suspend fun changeTask(groupId: Long, taskId: Long, title: String, description: String, start: String, end: String, difficult: Byte, reminder: String, token: String = CurrentUser.token): Long

    @PUT("")
    suspend fun changeSolution(groupId: Long, taskId: Long, solId: Long, title: String, description: String, token: String = CurrentUser.token): Long
}

object RemoteApi {
    val retrofitService : RemoteApiService by lazy {
        retrofit.create(RemoteApiService::class.java)
    }
}