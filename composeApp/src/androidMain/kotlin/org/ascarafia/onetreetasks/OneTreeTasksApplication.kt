package org.ascarafia.onetreetasks

import android.app.Application
import org.ascarafia.onetreetasks.application.di.initKoin
import org.koin.android.ext.koin.androidContext

class OneTreeTasksApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@OneTreeTasksApplication)
        }
    }
}