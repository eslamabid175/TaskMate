package com.example.taskmate.application

import android.app.Application
import com.example.taskmate.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TaskMateApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TaskMateApplication)
            modules(appModule)
        }
    }
}