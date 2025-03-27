package org.ascarafia.onetreetasks.application.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.ascarafia.onetreetasks.data.database.DataBaseFactory
import org.ascarafia.onetreetasks.data.database.TaskDatabase
import org.ascarafia.onetreetasks.domain.repository.TaskRepository
import org.ascarafia.onetreetasks.data.repository.TaskRepositoryImpl
import org.ascarafia.onetreetasks.ui.task_list.TaskListViewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.core.module.dsl.*
import org.koin.dsl.bind

val appModules: List<Module> get() = sharedModules + platformModule

expect val platformModule: Module

val sharedModules: List<Module> get() = listOf( viewModelsModule, dataBaseModule, repositoryModule )

val viewModelsModule = module {
    viewModelOf(::TaskListViewModel)
}

val dataBaseModule = module {
    single {
        get<DataBaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single {
        get<TaskDatabase>().taskDao
    }
}

val repositoryModule = module {
    singleOf(::TaskRepositoryImpl).bind<TaskRepository>()
}