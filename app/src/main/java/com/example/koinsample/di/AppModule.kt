package com.example.koinsample.di

import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import org.koin.dsl.module

val appModule = module {
    single { SplitInstallManagerFactory.create(get()) }
}
