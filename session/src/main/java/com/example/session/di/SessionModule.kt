package com.example.session.di

import com.example.session.LoggedSessionManager
import com.example.session.UnloggedSessionManager
import com.example.session.ext.SESSION_SCOPE_NAME
import com.example.session.ext.getSessionScope
import com.example.session.model.Session
import com.example.session.util.FakeSessionGenerator
import org.koin.core.qualifier.named
import org.koin.dsl.module

val sessionModule = module {
    single { FakeSessionGenerator() }

    scope(named(SESSION_SCOPE_NAME)) {
        scoped {
            val session = getSessionScope().getOrNull<Session>()
            if (session == null) {
                UnloggedSessionManager(fakeSessionGenerator = get())
            } else {
                LoggedSessionManager(session)
            }
        }
    }
}
