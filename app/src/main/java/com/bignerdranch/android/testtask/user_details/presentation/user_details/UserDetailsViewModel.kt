package com.bignerdranch.android.testtask.user_details.presentation.user_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.testtask.core.SingleLiveData
import com.bignerdranch.android.testtask.core.model.Result
import com.bignerdranch.android.testtask.core.model.UserRepo
import com.bignerdranch.android.testtask.user_details.domain.usecases.GetUserReposUseCase
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val getUserReposUseCase: GetUserReposUseCase
) : ViewModel() {

    val repos: MutableLiveData<List<UserRepo>> = MutableLiveData(listOf())
    val removeLastLoading: SingleLiveData<Unit> = SingleLiveData(Unit)

    val hasFailed: SingleLiveData<String> = SingleLiveData(null)

    private var pageToLoad = 1

    private var noInternet = false


    fun loadRepos(username: String) {
        viewModelScope.launch {
            getUserReposUseCase(username, pageToLoad++).collect { result ->
                when(result) {
                    is Result.Success -> {
                        if(result.data!!.isEmpty()) {
                            repos.value = repos.value!!.toMutableList().apply { removeLastOrNull() }
                            removeLastLoading.setValue(Unit)
                            return@collect
                        }
                        val oldValue = repos.value!!.toMutableList().apply {
                            if(isNotEmpty() && last() is UserRepo.LoadingRepo)
                                removeLast()
                        }
                        repos.value = oldValue + result.data
                        if(!noInternet) {
                            loadRepos(username)
                        }
                    }
                    is Result.Loading -> {
                        if(repos.value!!.isEmpty() || repos.value!!.last() !is UserRepo.LoadingRepo)
                            repos.value = repos.value!! + listOf<UserRepo>(UserRepo.LoadingRepo())
                    }
                    is Result.Error -> {
                        hasFailed.setValue(result.message!!)
                        noInternet = true
                    }
                }
            }
        }
    }
}