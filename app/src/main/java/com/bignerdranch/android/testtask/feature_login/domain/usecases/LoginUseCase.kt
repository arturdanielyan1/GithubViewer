package com.bignerdranch.android.testtask.feature_login.domain.usecases

import com.bignerdranch.android.testtask.core.model.Result
import com.bignerdranch.android.testtask.core.TOKEN
import com.bignerdranch.android.testtask.feature_login.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase (private val loginRepository: LoginRepository) {

    suspend operator fun invoke(token: String): Flow<Result<Unit>> {
//        loginRepository.setLoggedIn()
        return loginRepository.authenticate(token)
    }
}