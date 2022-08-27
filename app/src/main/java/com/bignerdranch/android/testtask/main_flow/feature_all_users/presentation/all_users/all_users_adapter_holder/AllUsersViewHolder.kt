package com.bignerdranch.android.testtask.main_flow.feature_all_users.presentation.all_users.all_users_adapter_holder

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.testtask.R
import com.bignerdranch.android.testtask.main_flow.feature_all_users.domain.model.UserData


class AllUsersViewHolder(inflater: LayoutInflater, parent: ViewGroup, typeLayout: Int) :
    RecyclerView.ViewHolder(inflater.inflate(typeLayout, parent, false)) {

        fun bind(user: UserData, bitmap: Bitmap?, onUserItemClick: (UserData) -> Unit) {
            if(itemViewType == R.layout.item_all_users) {
                itemView.apply {
                    findViewById<AppCompatImageView>(R.id.user_avatar_iv).setImageBitmap(bitmap)
                    findViewById<TextView>(R.id.username_tv).text = user.login
                    setOnClickListener {
                        onUserItemClick.invoke(user)
                    }
                }
            }
        }

    }