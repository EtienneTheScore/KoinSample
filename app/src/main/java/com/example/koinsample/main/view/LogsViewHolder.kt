package com.example.koinsample.main.view

import androidx.recyclerview.widget.RecyclerView
import com.example.koinsample.databinding.ItemLogBinding

class LogsViewHolder(private val binding: ItemLogBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindTo(message: String) {
        binding.message = message
    }
}
