package com.example.koinsample.di

import com.example.koinsample.main.di.mainModule

val appComponent = listOf(
    sessionModule,
    mainModule
)