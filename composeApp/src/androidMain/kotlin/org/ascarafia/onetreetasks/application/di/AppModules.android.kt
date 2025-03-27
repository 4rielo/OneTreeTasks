package org.ascarafia.onetreetasks.application.di

import org.ascarafia.onetreetasks.data.database.DataBaseFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single { DataBaseFactory(androidApplication()) }
    }