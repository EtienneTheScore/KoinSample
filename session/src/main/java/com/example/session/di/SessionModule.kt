package com.example.session.di

import com.example.session.LoggedSessionManager
import com.example.session.SessionManager
import com.example.session.UnloggedSessionManager
import com.example.session.model.Session
import com.example.session.util.FakeSessionGenerator
import org.koin.core.qualifier.named
import org.koin.dsl.module

val sessionModule = module {
    single { FakeSessionGenerator() }

    scope(named<SessionManager>()) {
        scoped {
            val session = getOrNull<Session>()
            if (session == null) {
                UnloggedSessionManager(fakeSessionGenerator = get())
            } else {
                LoggedSessionManager(session)
            }
        }
    }
}
