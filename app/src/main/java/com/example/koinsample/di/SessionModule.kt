package com.example.koinsample.di

import com.example.koinsample.SessionManager
import org.koin.core.Koin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.dsl.module

val sessionModule = module {
    scope(named<SessionManager>()) {
        scoped { SessionManager(getOrNull()) }
    }
}

/**
 * Extension function so each module doesn't have to handle the session scope's name and id.
 */
fun Scope.getSessionManager(): SessionManager {
    return getKoin().getSessionScope().get()
}

@Synchronized
fun Koin.getSessionScope(): Scope {
    return getOrCreateScope(SESSION_SCOPE_ID, named<SessionManager>())
}

private const val SESSION_SCOPE_ID = "SESSION"
