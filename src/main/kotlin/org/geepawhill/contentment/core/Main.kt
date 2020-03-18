package org.geepawhill.contentment.core

import javafx.scene.text.Font
import javafx.stage.Stage
import tornadofx.*

class Main : App(MainView::class) {
    override fun start(stage: Stage) {
        super.start(stage)
        preloadFontFile("/org/geepawhill/scripts/ChewedPenBB.otf")
        preloadFontFile("/org/geepawhill/scripts/ChewedPenBB_ital.otf")
        FX.primaryStage.isMaximized = true
        FX.primaryStage.fullScreenExitHint = ""

    }

    private fun preloadFontFile(fontfile: String) {
        Font.loadFont(Main::class.java.getResource(fontfile).toExternalForm(), 50.0)
    }
}