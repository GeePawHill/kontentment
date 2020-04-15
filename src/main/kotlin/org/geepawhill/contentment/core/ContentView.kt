package org.geepawhill.contentment.core

import javafx.scene.input.MouseButton
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.scene.media.MediaView
import javafx.scene.paint.Color
import javafx.util.Duration
import org.geepawhill.contentment.jfx.AspectRatioConstraint
import org.geepawhill.contentment.player.Player
import org.geepawhill.contentment.rhythm.RhythmListener
import tornadofx.*

class ContentView(private val player: Player) : View(), RhythmListener {
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
        // make the mediaplayer when the script changes
        val uri = player.script.media
        if (uri != "") {
            val m = Media(player.script.media)
            val mediaPlayer = MediaPlayer(m)
            mediaPlayer.pause()
            mediaView.mediaPlayer = mediaPlayer
            mediaView.show()
            player.rhythm.addListener(this)
        } else mediaView.hide()
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

    override fun pause() {
        mediaView.mediaPlayer.pause()
    }

    override fun play() {
        mediaView.mediaPlayer.play()
    }

    override fun seek(ms: Long) {
        mediaView.mediaPlayer.seek(Duration.millis(ms.toDouble()))
    }
}