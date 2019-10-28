package com.example.userdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.session.SessionManager

class UserDetailsViewModel(sessionManager: SessionManager) : ViewModel() {

    val usernameLiveData = liveData {
        check(sessionManager is SessionManager.Logged) { "Must be logged in." }
        emit(sessionManager.session.username)
    }
}
