package com.example.koinsample

import android.app.Application
import com.example.koinsample.di.KoinLogger
import com.example.koinsample.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE
import org.koin.core.module.Module
import timber.log.Timber
import timber.log.Timber.DebugTree

val koinLogger = KoinLogger()

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        configureTimber()
        configureKoin()
    }

    private fun provideComponent(): List<Module> {
        return appComponent
    }

    private fun configureTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    private fun configureKoin() {
        startKoin {
            // Custom logger for this sample. In a real app, use androidLogger() instead.
            logger(koinLogger)

            //inject Android context
            androidContext(this@App)
            // use modules
            modules(provideComponent())
        }
    }
}
