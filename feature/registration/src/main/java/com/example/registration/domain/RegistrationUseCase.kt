package com.example.registration.domain

import com.example.data.CurrentUser
import com.example.registration.R
import com.example.utils.strings.StringResourceProvider
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(
    private val repository: RegistrationRepository,
    private val stringRes: StringResourceProvider
) {
    suspend operator fun invoke(email: String, password: String, fullName: String): Result<CurrentUser> {
        if (email.isBlank() || !email.contains("@")) {
            return Result.failure(IllegalArgumentException(stringRes.getString(R.string.reg_use_case_email_is_blank)))
        }
        if (password.isBlank() || password.length < 6) {
            return Result.failure(IllegalArgumentException(stringRes.getString(R.string.reg_use_case_password_is_blank)))
        }
        if (fullName.isBlank()) {
            return Result.failure(IllegalArgumentException(stringRes.getString(R.string.reg_use_case_full_name_is_blank)))
        }
        return repository.register(email.trim(), password, fullName)
    }
}