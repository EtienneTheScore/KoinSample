package com.example.koinsample.di

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE

private var logId = 0

sealed class Log(val id: Int, open val message: String) {
    data class Message(val level: Level, override val message: String) : Log(logId++, message)
    data class Action(override val message: String) : Log(logId++, message)
}

class KoinLogger : Logger(Level.DEBUG) {
    private val _logs = MutableLiveData<List<Log>>()
    val logs: LiveData<List<Log>> = _logs

    override fun log(level: Level, msg: MESSAGE) {
        log(Log.Message(level, msg))
    }

    fun log(log: Log) {
        _logs.value = _logs.value.orEmpty().toMutableList().apply { add(log) }
    }
}
