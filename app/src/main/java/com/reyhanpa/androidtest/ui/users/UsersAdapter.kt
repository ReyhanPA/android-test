package com.reyhanpa.androidtest.ui.users

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.reyhanpa.androidtest.data.remote.response.DataItem
import com.reyhanpa.androidtest.databinding.ItemUsernameBinding
import com.reyhanpa.androidtest.ui.welcome.WelcomeActivity
import com.reyhanpa.androidtest.utils.DiffUtilCallback

class UsersAdapter : PagingDataAdapter<DataItem, UsersAdapter.UsersViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding = ItemUsernameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user)
        }
    }

    class UsersViewHolder(private val binding: ItemUsernameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem){
            Glide.with(binding.root.context)
                .load(user.avatar)
                .into(binding.imgUsername)
            val username = user.firstName + " " + user.lastName
            binding.tvUsername.text = username
            binding.tvEmail.text = user.email
            itemView.setOnClickListener {
                val intent = Intent(binding.root.context, WelcomeActivity::class.java)
                intent.putExtra("EXTRA_USERNAME", username)
                binding.root.context.startActivity(intent)
            }
        }
    }
}