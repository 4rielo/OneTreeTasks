package org.ascarafia.onetreetasks

import androidx.compose.ui.window.ComposeUIViewController
import org.ascarafia.onetreetasks.application.di.initKoin

fun MainViewController() = ComposeUIViewController (
    configure = {
        initKoin()
    }
) {
    App()
}