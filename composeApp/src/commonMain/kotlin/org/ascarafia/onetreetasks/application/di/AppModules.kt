package org.ascarafia.onetreetasks.application.di

import org.ascarafia.onetreetasks.ui.task_list.TaskListViewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.core.module.dsl.*

val appModules: List<Module> get() = sharedModules

val sharedModules: List<Module> get() = listOf( viewModelsModule )

val viewModelsModule = module {
    viewModelOf(::TaskListViewModel)
}