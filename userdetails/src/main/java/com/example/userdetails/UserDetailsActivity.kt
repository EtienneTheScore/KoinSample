package com.example.userdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.koinsample.di.Log
import com.example.koinsample.koinLogger
import com.example.userdetails.databinding.ActivityUserDetailsBinding
import com.example.userdetails.di.injectFeature
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailsActivity : AppCompatActivity() {

    private val viewModel by viewModel<UserDetailsViewModel>()
    private lateinit var binding: ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        koinLogger.log(Log.Action("[UserDetailsActivity] ON CREATE"))
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_details)
        injectFeature()
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
    }
}
