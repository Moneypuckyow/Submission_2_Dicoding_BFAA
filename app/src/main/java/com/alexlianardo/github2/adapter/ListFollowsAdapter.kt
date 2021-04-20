package com.alexlianardo.github2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexlianardo.github2.databinding.UsersItemBinding
import com.alexlianardo.github2.model.Users
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class ListFollowsAdapter(private val listFollows : ArrayList<Users>) : RecyclerView.Adapter<ListFollowsAdapter.ListViewHolder>(){


    inner class ListViewHolder(private val binding: UsersItemBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(users: Users) {
            with(binding){
                Glide.with(itemView.context)
                        .load(users.photo)
                        .apply(RequestOptions().override(200, 200))
                        .into(imgUser)
                txtUsername.text = users.username
                txtName.text = users.name
                txtFollowing.text = users.following
                txtFollowers.text = users.followers
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ListViewHolder {
        val binding = UsersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listFollows[position])
    }


    override fun getItemCount(): Int = listFollows.size
}