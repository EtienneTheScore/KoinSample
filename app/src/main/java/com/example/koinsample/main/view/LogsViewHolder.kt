package com.example.koinsample.main.view

import androidx.recyclerview.widget.RecyclerView
import com.example.koinsample.databinding.ItemLogBinding
import com.example.koinsample.di.Log
import org.koin.core.logger.Level

class LogsViewHolder(private val binding: ItemLogBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindTo(log: Log) {
        val backgroundColor = when (log) {
            is Log.Message -> android.R.color.white
            is Log.Action -> android.R.color.black
        }
        val textColor = when (log) {
            is Log.Message -> {
                when (log.level) {
                    Level.DEBUG -> android.R.color.holo_green_dark
                    Level.INFO -> android.R.color.holo_orange_dark
                    Level.ERROR -> android.R.color.holo_red_dark
                }
            }
            is Log.Action -> android.R.color.white
        }

        binding.message = log.message
        binding.backgroundColor = backgroundColor
        binding.textColor = textColor
    }
}
