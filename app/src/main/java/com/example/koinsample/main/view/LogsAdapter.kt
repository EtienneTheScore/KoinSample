package com.example.koinsample.main.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.koinsample.databinding.ItemLogBinding
import com.example.koinsample.di.Log

class LogsAdapter : ListAdapter<Log, LogsViewHolder>(LogsDiffCall()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLogBinding.inflate(layoutInflater, parent, false)
        return LogsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LogsViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}
