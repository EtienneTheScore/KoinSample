package com.example.koinsample.main.di

import com.example.koinsample.di.getSessionManager
import com.example.koinsample.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainViewModel(getSessionManager()) }
}
