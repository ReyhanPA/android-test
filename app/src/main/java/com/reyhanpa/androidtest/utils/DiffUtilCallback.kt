package com.reyhanpa.androidtest.utils

import androidx.recyclerview.widget.DiffUtil
import com.reyhanpa.androidtest.data.remote.response.DataItem

object DiffUtilCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}