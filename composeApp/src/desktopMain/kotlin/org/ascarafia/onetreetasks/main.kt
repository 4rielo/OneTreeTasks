package org.ascarafia.onetreetasks

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.ascarafia.onetreetasks.application.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "OneTreeTasks",
    ) {
        App()
    }
}