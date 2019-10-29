package com.example.koinsample.main.view

import androidx.recyclerview.widget.DiffUtil
import com.example.koinsample.di.Log

class LogsDiffCall : DiffUtil.ItemCallback<Log>() {

    override fun areItemsTheSame(oldItem: Log, newItem: Log): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Log, newItem: Log): Boolean {
        return oldItem == newItem
    }
}
