package com.example.session.util

import com.example.session.model.Session

internal class FakeSessionGenerator {
    var fakeSessionId = 0

    fun generateFakeSession(): Session {
        return Session("FakeUser${fakeSessionId++}")
    }
}
