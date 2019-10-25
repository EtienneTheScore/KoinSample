package com.example.koinsample.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.koinsample.SessionManager
import com.example.koinsample.koinLogger

class MainViewModel(private val sessionManager: SessionManager) : ViewModel() {

    lateinit var reload: (() -> Unit)

    data class State(
        val logs: LiveData<List<String>>,
        val logInOffText: String,
        val isUserDetailsButtonEnabled: Boolean
    )

    val state = State(
        logs = koinLogger.logs,
        logInOffText = if (sessionManager.isLogged) "LOG OUT" else "LOG IN",
        isUserDetailsButtonEnabled = sessionManager.isLogged
    )

    fun onLogInOffClick() {
        if (sessionManager.isLogged) {
            sessionManager.logOff()
        } else {
            sessionManager.logIn()
        }
        reload()
    }

    fun onUserDetailsClick() {
    }
}
