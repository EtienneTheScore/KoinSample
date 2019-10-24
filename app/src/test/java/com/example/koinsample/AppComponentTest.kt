package com.example.koinsample

import com.example.koinsample.di.appComponent
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules

@RunWith(JUnit4::class)
class AppComponentTest {

    /**
     * Check if every modules are well configured with all definitions bounded.
     */
    @Test
    fun `check AppComponent modules`() {
        koinApplication { modules(appComponent) }.checkModules()
    }
}
