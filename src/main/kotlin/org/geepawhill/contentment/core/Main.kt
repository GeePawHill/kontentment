package org.geepawhill.contentment.core

import org.geepawhill.contentment.player.Player

import javafx.application.Application
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.text.Font
import javafx.stage.Stage

class Main : Application() {

    override fun start(stage: Stage) {
        try {
            preloadFontFile("/org/geepawhill/scripts/SAMUELS.TTF")
            preloadFontFile("/org/geepawhill/scripts/CaveatBrush-Regular.TTF")
            preloadFontFile("/org/geepawhill/scripts/gloriahallelujah.TTF")
            preloadFontFile("/org/geepawhill/scripts/GoodDog.otf")
            preloadFontFile("/org/geepawhill/scripts/belligerent.ttf")
            preloadFontFile("/org/geepawhill/scripts/WCManoNegraBoldBta.otf")
            preloadFontFile("/org/geepawhill/scripts/Anysome.otf")
            preloadFontFile("/org/geepawhill/scripts/Anysome italic.otf")
            preloadFontFile("/org/geepawhill/scripts/ChewedPenBB.otf")
            preloadFontFile("/org/geepawhill/scripts/ChewedPenBB_ital.otf")
            val player = Player()
            val mainView = MainView(stage, player)
            val scene = Scene(mainView.node)
            stage.scene = scene
            stage.isMaximized = true
            stage.fullScreenExitHint = ""
            stage.show()
            player.load(OptimizingForCollaboration().make())
        } catch (e: Exception) {
            e.printStackTrace()
            Platform.exit()
        }

    }

    private fun preloadFontFile(fontfile: String) {
        Font.loadFont(Main::class.java.getResource(fontfile).toExternalForm(), 50.0)
    }
}
