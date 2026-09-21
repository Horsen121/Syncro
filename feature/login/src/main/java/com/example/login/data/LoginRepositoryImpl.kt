package com.example.login.data

import com.example.data.CurrentUser
import com.example.data.CurrentUserRepository
import com.example.feature.login.R
import com.example.login.domain.LoginRepository
import com.example.network.AuthApi
import com.example.network.dto.LoginRequest
import com.example.utils.data.TokenManager
import com.example.utils.strings.StringResourceProvider
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val tokenManager: TokenManager,
    private val currentUserRepository: CurrentUserRepository,
    private val stringRes: StringResourceProvider
) : LoginRepository {

    override suspend fun login(email: String, password: String): Result<CurrentUser> {
        return runCatching {
            val response = authApi.login(LoginRequest(email, password))
            if (response.isSuccessful  && response.body() != null) {
                val body = response.body()!!

                tokenManager.saveToken(body.accessToken, body.refreshToken)
                val user = CurrentUser(
                    body.userId,
                    body.fullName,
                    email
                )
                currentUserRepository.setSession(user)

                user
            } else {
                val errorMsg = response.errorBody()?.string().takeUnless { it.isNullOrBlank() }
                    ?: stringRes.getStringWithParams(R.string.login_repository_error, response.code().toString())
                throw Exception(errorMsg)
            }
        }
    }
}