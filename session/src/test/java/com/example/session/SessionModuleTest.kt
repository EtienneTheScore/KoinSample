package com.example.session

import com.example.session.di.sessionModule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules

@RunWith(JUnit4::class)
class SessionModuleTest {

    @Test
    fun `check sessionModule`() {
        koinApplication { modules(sessionModule) }.checkModules()
    }
}
