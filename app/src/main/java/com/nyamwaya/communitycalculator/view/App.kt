package com.nyamwaya.communitycalculator.view

import android.app.Application
import com.nyamwaya.communitycalculator.di.viewModelScope
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        configureKoin()
    }

    private fun configureKoin() {
        startKoin {
            androidContext(this@App)
            modules(viewModelScope)
        }
    }
}