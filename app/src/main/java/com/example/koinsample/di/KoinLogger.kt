package com.example.koinsample.di

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE
import java.util.*

class KoinLogger : Logger(Level.INFO) {
    private val _logs = MutableLiveData<List<String>>()
    val logs: LiveData<List<String>> = _logs

    override fun log(level: Level, msg: MESSAGE) {
        val timestamp = Date().toString()
        _logs.value = _logs.value.orEmpty().toMutableList().apply { add("[$timestamp]\n$msg") }
    }
}
