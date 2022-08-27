package com.bignerdranch.android.testtask.main_flow.feature_my_repos.presentation.my_repos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.testtask.core.SingleLiveData
import com.bignerdranch.android.testtask.core.model.Result
import com.bignerdranch.android.testtask.core.model.UserRepo
import com.bignerdranch.android.testtask.main_flow.feature_my_repos.domain.usecases.GetReposUseCase
import kotlinx.coroutines.launch

class MyReposViewModel(
    private val getReposUseCase: GetReposUseCase
) : ViewModel() {

    val repos: MutableLiveData<List<UserRepo>> = MutableLiveData(emptyList())
    val removeLastLoadingItem: SingleLiveData<Unit> = SingleLiveData(Unit)

    val hasFailed: SingleLiveData<String> = SingleLiveData()

    private var noInternet = false

    private var pageToLoad = 1

    init {
        loadRepos()
    }

    private fun loadRepos() {
        viewModelScope.launch {
            getReposUseCase(pageToLoad++).collect { result ->
                when(result) {
                    is Result.Success -> {
                        if(result.data!!.isEmpty()) {
                            repos.value = repos.value!!.toMutableList().apply { removeLastOrNull() }
                            removeLastLoadingItem.setValue(Unit)
                            return@collect
                        }
                        val oldValue = repos.value!!.toMutableList().apply {
                            if(isNotEmpty() && last() is UserRepo.LoadingRepo)
                            removeLast()
                        }
                        repos.value = oldValue + result.data
                        if(!noInternet) {
                            loadRepos()
                        }
                    }
                    is Result.Error -> {
                        hasFailed.setValue(result.message!!)
                        noInternet = true
                    }
                    is Result.Loading -> {
                        if(repos.value!!.isEmpty() || repos.value!!.last() !is UserRepo.LoadingRepo)
                        repos.value = repos.value!! + listOf<UserRepo>(UserRepo.LoadingRepo())
                    }
                }
            }
        }
    }
}