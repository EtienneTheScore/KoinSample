package com.example.koinsample.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.koinsample.R
import com.example.koinsample.databinding.ActivityMainBinding
import com.example.koinsample.main.view.LogsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        binding.logsRv.adapter = LogsAdapter()
    }

}
