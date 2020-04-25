package org.geepawhill.contentment.core

import javafx.scene.Parent
import javafx.scene.text.Text
import org.geepawhill.contentment.player.Player
import org.geepawhill.contentment.rhythm.RhythmListener
import tornadofx.*

class VlcjView(private val player: Player) : View(), RhythmListener {
    lateinit var status: Text

    override val root: Parent = stackpane {
        status = text("This is the VlcjView")
        player.scriptProperty().addListener { _ ->
            player.rhythm.addListener(this@VlcjView)
        }
    }

    override fun pause() {
        status.text = "Pause"
    }

    override fun play() {
        status.text = "Play"
    }

    override fun seek(ms: Long) {
    }

    override fun frame() {
    }


}
