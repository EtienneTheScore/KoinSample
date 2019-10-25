package com.example.koinsample.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.koinsample.koinLogger
import com.example.session.SessionManager

class MainViewModel(private val sessionManager: SessionManager) : ViewModel() {

    lateinit var reload: (() -> Unit)

    data class State(
        val logs: LiveData<List<String>>,
        val logInOffText: String,
        val isUserDetailsButtonEnabled: Boolean
    )

    val state = State(
        logs = koinLogger.logs,
        logInOffText = if (sessionManager.isLoggedIn) "LOG OUT" else "LOG IN",
        isUserDetailsButtonEnabled = sessionManager.isLoggedIn
    )

    fun onLogInOffClick() {
        when (sessionManager) {
            is SessionManager.Logged -> sessionManager.logOff()
            is SessionManager.Unlogged -> sessionManager.logIn()
        }
        reload()
    }

    fun onUserDetailsClick() {
    }
}
