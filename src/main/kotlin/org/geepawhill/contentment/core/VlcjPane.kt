package org.geepawhill.contentment.core

import javafx.scene.Parent
import javafx.scene.canvas.Canvas
import javafx.scene.layout.Pane
import org.geepawhill.contentment.player.Player
import org.geepawhill.contentment.rhythm.RhythmListener
import org.geepawhill.contentment.vlcj.VlcjSurface
import tornadofx.*
import uk.co.caprica.vlcj.factory.MediaPlayerFactory
import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer
import java.io.File

class VlcjPane(private val player: Player) : Pane(), RhythmListener {
    private val surface = VlcjSurface()
    val ratio = 9.0 / 16.0
    val canvas = Canvas()
    private val mediaPlayerFactory: MediaPlayerFactory = MediaPlayerFactory()

    val mediaPlayer: EmbeddedMediaPlayer = mediaPlayerFactory.mediaPlayers().newEmbeddedMediaPlayer()

    val root: Parent = pane {
    }

    inner class FirstFrameListener : MediaPlayerEventAdapter() {
        override fun paused(mediaPlayer: MediaPlayer?) {
            runLater {
                mediaPlayer!!.controls().nextFrame()
                render()
            }
        }
    }

    init {
        mediaPlayer.videoSurface().set(surface)
        mediaPlayer.events().addMediaPlayerEventListener(FirstFrameListener())
        mediaPlayer.media().prepare(File("C:\\GeePawHillDotOrgWip\\wip\\ccs talks\\Beauty In Code 2020\\bic2020-raw-tonal.mp4").absolutePath)
        mediaPlayer.controls().setPosition(0f)
        mediaPlayer.controls().start()
        mediaPlayer.controls().pause()
        this += canvas
        canvas.widthProperty().addListener { _ ->
            runLater { render() }
        }
        canvas.heightProperty().addListener { _ ->
            runLater { render() }
        }
        player.scriptProperty().addListener { _ ->
            player.rhythm.addListener(this@VlcjPane)
        }
    }

    override fun layoutChildren() {
        val impliedHeightForWidth = ratio * width
        if (impliedHeightForWidth < height) {
            canvas.width = width
            canvas.height = impliedHeightForWidth
        } else {
            val impliedWidthForHeight = (1.0 / ratio) * height
            canvas.width = impliedWidthForHeight
            canvas.height = height
        }
        canvas.translateX = (width - canvas.width) / 2.0
        canvas.translateY = (height - canvas.height) / 2.0
    }

    override fun pause() {
        mediaPlayer.controls().pause()
    }

    override fun play() {
        mediaPlayer.controls().play()
    }

    override fun seek(ms: Long) {
        val position = ms.toFloat() / player.script.length.toFloat()
        mediaPlayer.controls().setPosition(position)
        render()
    }

    override fun frame() {
        runLater {
            render()
        }
    }

    private fun render() {
        val g = canvas.graphicsContext2D
        val imageWidth = surface.image.width
        val imageHeight = surface.image.height
        val sx = canvas.width / imageWidth
        val sy = canvas.height / imageHeight
        val sf = Math.max(sx, sy)
        val scaledW = imageWidth * sf
        val scaledH = imageHeight * sf
        val ax = g.transform
        g.translate(
                (canvas.width - scaledW) / 2,
                (canvas.height - scaledH) / 2
        )
        if (sf != 1.0) {
            g.scale(sf, sf)
        }

        surface.update()
        g.drawImage(surface.image, 0.0, 0.0)
        g.transform = ax
    }
}
