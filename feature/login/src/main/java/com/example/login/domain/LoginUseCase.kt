package com.example.login.domain

import com.example.data.CurrentUser
import com.example.feature.login.R
import com.example.utils.strings.StringResourceProvider
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository,
    private val stringRes: StringResourceProvider
) {
    suspend operator fun invoke(email: String, password: String): Result<CurrentUser> {
        if (email.isBlank() || !email.contains("@")) {
            return Result.failure(IllegalArgumentException(stringRes.getString(R.string.login_use_case_email_is_blank)))
        }
        if (password.isBlank() || password.length < 6) {
            return Result.failure(IllegalArgumentException(stringRes.getString(R.string.login_use_case_password_is_blank)))
        }
        return repository.login(email.trim(), password)
    }
}