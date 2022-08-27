package com.bignerdranch.android.testtask.feature_login.domain.usecases

import com.bignerdranch.android.testtask.core.model.Result
import com.bignerdranch.android.testtask.feature_login.domain.repository.LoginRepository

class IsLoggedInUseCase(
    private val loginRepository: LoginRepository
) {

    suspend operator fun invoke(): Result<Boolean> {
        return loginRepository.isLoggedIn()
    }
}