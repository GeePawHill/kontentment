package org.geepawhill.contentment.core

import javafx.scene.Parent
import javafx.scene.canvas.Canvas
import javafx.scene.text.Text
import org.geepawhill.contentment.player.Player
import org.geepawhill.contentment.rhythm.RhythmListener
import org.geepawhill.contentment.vlcj.VlcjSurface
import tornadofx.*
import uk.co.caprica.vlcj.factory.MediaPlayerFactory
import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer
import java.io.File

class VlcjView(private val player: Player) : View(), RhythmListener {
    private lateinit var status: Text
    private lateinit var canvas: Canvas
    private val surface = VlcjSurface()
    private val mediaPlayerFactory: MediaPlayerFactory = MediaPlayerFactory()

    val mediaPlayer: EmbeddedMediaPlayer = mediaPlayerFactory.mediaPlayers().newEmbeddedMediaPlayer()

    override val root: Parent = stackpane {
        status = text("This is the VlcjView")
        val canvasPane = CanvasPane()
        this += canvasPane
        canvas = canvasPane.canvas
        canvas.widthProperty().addListener { _ ->
            runLater { render() }
        }
        canvas.heightProperty().addListener { _ ->
            runLater { render() }
        }
        player.scriptProperty().addListener { _ ->
            player.rhythm.addListener(this@VlcjView)
        }
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
    }

    override fun pause() {
        status.text = "Pause"
        mediaPlayer.controls().pause()
    }

    override fun play() {
        status.text = "Play"
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
