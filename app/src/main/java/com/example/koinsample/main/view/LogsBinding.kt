package com.example.koinsample.main.view

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object LogsBinding {

    @BindingAdapter("items")
    @JvmStatic
    fun setItems(recyclerView: RecyclerView, resource: List<String>?) {
        resource?.let((recyclerView.adapter as LogsAdapter)::submitList)
    }
}