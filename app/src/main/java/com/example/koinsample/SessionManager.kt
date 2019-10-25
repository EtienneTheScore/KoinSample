package com.example.koinsample

import com.example.koinsample.di.getSessionScope
import com.example.koinsample.model.Session
import org.koin.core.KoinComponent

class SessionManager(private val session: Session?) : KoinComponent {

    val isLogged = session != null

    fun logIn() {
        if (session != null) return
        reloadScope(Session("TestUser"))
    }

    fun logOff() {
        reloadScope(null)
    }

    @Synchronized
    private fun reloadScope(session: Session?) {
        getKoin().getSessionScope().close()
        session?.let {
            getKoin().getSessionScope().declare(it)
        }
    }
}
