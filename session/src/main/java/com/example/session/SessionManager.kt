package com.example.session

import com.example.session.ext.reloadSessionScope
import com.example.session.model.Session
import com.example.session.util.FakeSessionGenerator
import org.koin.core.KoinComponent

/**
 * Simple session manager.
 *
 * It extends [KoinComponent] in order to easily access [Koin]'s instance.
 */
sealed class SessionManager(val isLoggedIn: Boolean) : KoinComponent {
    abstract class Logged : SessionManager(true) {
        abstract val session: Session
        abstract fun logOff()
    }

    abstract class Unlogged : SessionManager(false) {
        abstract fun logIn()
    }
}

internal class LoggedSessionManager(override val session: Session) : SessionManager.Logged() {
    override fun logOff() {
        // Reload session scope without a session
        getKoin().reloadSessionScope(null)
    }
}

internal class UnloggedSessionManager(private val fakeSessionGenerator: FakeSessionGenerator) :
    SessionManager.Unlogged() {
    override fun logIn() {
        val fakeSession = fakeSessionGenerator.generateFakeSession()
        // Reload session scope with a new session
        getKoin().reloadSessionScope(fakeSession)
    }
}
