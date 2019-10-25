package com.example.koinsample.main.di

import com.example.koinsample.main.MainViewModel
import com.example.session.ext.getSessionScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainViewModel(sessionManager = getSessionScope().get()) }
}
