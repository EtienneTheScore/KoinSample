package com.example.koinsample.main

import androidx.lifecycle.ViewModel
import com.example.koinsample.koinLogger

class MainViewModel : ViewModel() {

    val logs = koinLogger.logs
}
