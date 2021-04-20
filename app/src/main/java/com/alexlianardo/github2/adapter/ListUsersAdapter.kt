package com.alexlianardo.github2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexlianardo.github2.model.Users
import com.bumptech.glide.Glide
import com.alexlianardo.github2.databinding.UsersItemBinding
import com.bumptech.glide.request.RequestOptions

class ListUsersAdapter(private val listUsers: ArrayList<Users>) : RecyclerView.Adapter<ListUsersAdapter.ListViewHolder>() {

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Users)
    }

    inner class ListViewHolder(private val binding: UsersItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(users: Users) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(users.photo)
                    .apply(RequestOptions().override(250, 250))
                    .into(imgUser)
                txtName.text = users.name
                txtUsername.text = users.username
                txtFollowers.text = users.followers
                txtFollowing.text = users.following
                itemView.setOnClickListener{ onItemClickCallback?.onItemClicked(users)}
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = UsersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUsers[position])
    }

    override fun getItemCount(): Int = listUsers.size
    private var onItemClickCallback: OnItemClickCallback? = null
}