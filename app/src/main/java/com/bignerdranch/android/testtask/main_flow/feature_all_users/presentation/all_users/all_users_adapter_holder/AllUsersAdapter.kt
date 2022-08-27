package com.bignerdranch.android.testtask.main_flow.feature_all_users.presentation.all_users.all_users_adapter_holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.testtask.R
import com.bignerdranch.android.testtask.main_flow.feature_all_users.domain.model.UserData

class AllUsersAdapter (private val inflater: LayoutInflater,
    private val onUserItemClick: (UserData) -> Unit) : RecyclerView.Adapter<AllUsersViewHolder>() {


    var users: MutableList<UserData> = mutableListOf(UserData.Loading())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllUsersViewHolder {
        return AllUsersViewHolder(inflater, parent, viewType)
    }

    override fun onBindViewHolder(holder: AllUsersViewHolder, position: Int) {
        holder.bind(users[position], users[position].avatarBitmap, onUserItemClick)
    }

    override fun getItemCount() = users.size

    override fun getItemViewType(position: Int) =
        if(users[position] !is UserData.Loading) R.layout.item_all_users
        else R.layout.item_loading
}