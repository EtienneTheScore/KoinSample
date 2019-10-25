package com.example.koinsample.di

import com.example.koinsample.main.di.mainModule
import com.example.session.di.sessionModule

val appComponent = listOf(
    sessionModule,
    mainModule
)