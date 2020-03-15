package org.geepawhill.contentment.core

import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.text.Font
import javafx.stage.Stage
import org.geepawhill.contentment.player.Player

class Main : Application() {

    override fun start(stage: Stage) {
        try {
            preloadFontFile("/org/geepawhill/scripts/ChewedPenBB.otf")
            preloadFontFile("/org/geepawhill/scripts/ChewedPenBB_ital.otf")
            val player = Player()
            val mainView = MainView(stage, player)
            val scene = Scene(mainView.root)
            stage.scene = scene
            stage.isMaximized = true
            stage.fullScreenExitHint = ""
            stage.show()
//            player.load(TddPremisesScript().make())
            player.load(BicScript().make())
//            player.load(OptimizingForCollaboration().make())
            player.load(BicScript().make())
        } catch (e: Exception) {
            e.printStackTrace()
            Platform.exit()
        }

    }

    private fun preloadFontFile(fontfile: String) {
        Font.loadFont(Main::class.java.getResource(fontfile).toExternalForm(), 50.0)
    }
}
