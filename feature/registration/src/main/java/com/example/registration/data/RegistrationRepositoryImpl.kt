package com.example.registration.data

import com.example.data.CurrentUser
import com.example.data.CurrentUserRepository
import com.example.network.AuthApi
import com.example.network.dto.RegisterRequest
import com.example.registration.R
import com.example.registration.domain.RegistrationRepository
import com.example.utils.data.TokenManager
import com.example.utils.strings.StringResourceProvider
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val tokenManager: TokenManager,
    private val currentUserRepository: CurrentUserRepository,
    private val stringRes: StringResourceProvider
) : RegistrationRepository {

    override suspend fun register(
        email: String,
        password: String,
        fullName: String
    ): Result<CurrentUser> {
        return runCatching {
            val response = authApi.register(RegisterRequest(email, password, fullName))
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
                    ?: stringRes.getStringWithParams(R.string.reg_repository_error, response.code().toString())
                throw Exception(errorMsg)
            }
        }
    }
}