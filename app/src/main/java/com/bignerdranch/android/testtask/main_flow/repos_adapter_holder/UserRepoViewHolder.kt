package com.bignerdranch.android.testtask.main_flow.repos_adapter_holder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.testtask.R
import com.bignerdranch.android.testtask.core.model.UserRepo

class UserRepoViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int, private val context: Context) :
    RecyclerView.ViewHolder(
        inflater.inflate(
            if(viewType == UserRepoAdapter.NORMAL_REPO) R.layout.item_user_repo
            else R.layout.item_loading, parent, false
        )
    )
{

    fun bind(userRepo: UserRepo) {
        if(itemViewType == UserRepoAdapter.NORMAL_REPO) {
            itemView.findViewById<TextView>(R.id.repo_name_tv).text =
                context.getString(R.string.repo_name, userRepo.name)

            itemView.findViewById<TextView>(R.id.repo_main_language).text =
                context.getString(R.string.repo_main_language, userRepo.language ?: "unknown") //not always non-null
        }
    }

}