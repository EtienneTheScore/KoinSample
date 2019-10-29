package com.example.koinsample.main.view

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.koinsample.di.Log

object LogsBinding {

    @BindingAdapter("items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, resource: List<Log>?) {
        resource?.let((recyclerView.adapter as LogsAdapter)::submitList)
    }
}