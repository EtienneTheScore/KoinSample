package com.example.userdetails.di

import com.example.session.ext.getSessionScope
import com.example.userdetails.UserDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

private val userDetailsFeatureModule = module {
    viewModel { UserDetailsViewModel(sessionManager = getSessionScope().get()) }
}

private val loadFeature by lazy {
    loadKoinModules(userDetailsFeatureModule)
}

internal fun injectFeature() = loadFeature
