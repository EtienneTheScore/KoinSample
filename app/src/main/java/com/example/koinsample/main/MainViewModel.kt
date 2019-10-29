package com.example.koinsample.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.koinsample.di.Log
import com.example.koinsample.koinLogger
import com.example.session.SessionManager

class MainViewModel(private val sessionManager: SessionManager) : ViewModel() {

    var reload: (() -> Unit)? = null
    var loadAndLaunchUserDetails: (() -> Unit)? = null

    data class State(
        val logs: LiveData<List<Log>>,
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
            is SessionManager.Logged -> {
                koinLogger.log(Log.Action("ON LOGOFF CLICKED"))
                sessionManager.logOff()
            }
            is SessionManager.Unlogged -> {
                koinLogger.log(Log.Action("ON LOGIN CLICKED"))
                sessionManager.logIn()
            }
        }
        reload?.invoke()
    }

    fun onUserDetailsClick() {
        koinLogger.log(Log.Action("ON USER DETAILS CLICKED"))
        loadAndLaunchUserDetails?.invoke()
    }
}
