package org.geepawhill.contentment.core

import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.media.MediaView
import javafx.scene.paint.Color
import org.geepawhill.contentment.jfx.AspectRatioConstraint
import org.geepawhill.contentment.player.Player
import tornadofx.*

class ContentView(private val player: Player) : View() {
    private val mediaView = MediaView()

    override val root = pane {
        setPrefSize(1600.0, 900.0)
        background = Background(BackgroundFill(Color.BLACK, null, null))
        player.scriptProperty().addListener { _, _, _ -> scriptChanged() }
        this += mediaView
        AspectRatioConstraint(widthProperty(), heightProperty(), player.context().canvas.transforms, mediaView.fitWidthProperty())
        setOnMouseClicked { event -> mouseClicked(event) }
        this += player.context().canvas
    }

    private fun scriptChanged() {
        mediaView.mediaPlayer = player.script.mediaPlayer
    }

    private fun mouseClicked(event: MouseEvent) {
        if (event.isShiftDown && event.button == MouseButton.PRIMARY) {
            player.forward()
            return
        }
        if (event.isControlDown && event.button == MouseButton.PRIMARY) {
            player.play()
            return
        }
        if (event.button == MouseButton.SECONDARY)
            player.backward()
        else
            player.playOne()
    }
}