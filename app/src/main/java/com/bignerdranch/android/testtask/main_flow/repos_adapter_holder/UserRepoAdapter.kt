package com.bignerdranch.android.testtask.main_flow.repos_adapter_holder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.testtask.core.model.UserRepo

class UserRepoAdapter(private val inflater: LayoutInflater, private val context: Context) : RecyclerView.Adapter<UserRepoViewHolder>() {

    companion object {
        const val NORMAL_REPO = 0
        const val LOADING_REPO = 1
    }

    var reposList: MutableList<UserRepo> = mutableListOf(/*UserRepo.LoadingRepo()*/)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepoViewHolder {
        return UserRepoViewHolder(inflater, parent, viewType, context)
    }

    override fun onBindViewHolder(holder: UserRepoViewHolder, position: Int) {
        holder.bind(reposList[position])
    }

    override fun getItemCount() = reposList.size

    override fun getItemViewType(position: Int) =
        if(position == reposList.size-1 && reposList[position] is UserRepo.LoadingRepo)
            LOADING_REPO
        else
            NORMAL_REPO

}