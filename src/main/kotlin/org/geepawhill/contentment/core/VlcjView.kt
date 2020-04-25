package org.geepawhill.contentment.core

import javafx.scene.Parent
import javafx.scene.canvas.Canvas
import javafx.scene.image.PixelBuffer
import javafx.scene.image.PixelFormat
import javafx.scene.image.WritableImage
import javafx.scene.image.WritablePixelFormat
import javafx.scene.text.Text
import org.geepawhill.contentment.player.Player
import org.geepawhill.contentment.rhythm.RhythmListener
import tornadofx.*
import uk.co.caprica.vlcj.factory.MediaPlayerFactory
import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer
import uk.co.caprica.vlcj.player.embedded.videosurface.CallbackVideoSurface
import uk.co.caprica.vlcj.player.embedded.videosurface.VideoSurfaceAdapters
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.BufferFormat
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.BufferFormatCallback
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.RenderCallback
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.format.RV32BufferFormat
import java.io.File
import java.nio.ByteBuffer

class VlcjView(private val player: Player) : View(), RhythmListener {
    private lateinit var status: Text
    private lateinit var canvas: Canvas
    private val pixelFormat: WritablePixelFormat<ByteBuffer?> = PixelFormat.getByteBgraPreInstance()
    private var bufferWidth = 0
    private var bufferHeight = 0

    private var pixelBuffer: PixelBuffer<*>? = null
    private var img: WritableImage? = null

    private val mediaPlayerFactory: MediaPlayerFactory = MediaPlayerFactory()
    val mediaPlayer: EmbeddedMediaPlayer = mediaPlayerFactory.mediaPlayers().newEmbeddedMediaPlayer()

    private inner class JavaFxVideoSurface internal constructor() : CallbackVideoSurface(JavaFxBufferFormatCallback(), JavaFxRenderCallback(), true, VideoSurfaceAdapters.getVideoSurfaceAdapter())

    private inner class JavaFxBufferFormatCallback : BufferFormatCallback {
        override fun getBufferFormat(sourceWidth: Int, sourceHeight: Int): BufferFormat {
            println("gbf")
            bufferWidth = sourceWidth
            bufferHeight = sourceHeight

            // This does not need to be done here, but you could set the video surface size to match the native video
            // size
            return RV32BufferFormat(sourceWidth, sourceHeight)
        }

        override fun allocatedBuffers(buffers: Array<ByteBuffer>) {
            // This is the new magic sauce, the native video buffer is used directly for the image buffer - there is no
            // full-frame buffer copy here
            println("ab")
            pixelBuffer = PixelBuffer(bufferWidth, bufferHeight, buffers[0], pixelFormat)
            img = WritableImage(pixelBuffer)
        }
    }

    // This is correct as far as it goes, but we need to use one of the timers to get smooth rendering (the timer is
    // handled by the demo sub-classes)
    private class JavaFxRenderCallback : RenderCallback {
        override fun display(mediaPlayer: MediaPlayer, nativeBuffers: Array<ByteBuffer>, bufferFormat: BufferFormat) {
        }
    }

    override val root: Parent = stackpane {
        status = text("This is the VlcjView")
        canvas = canvas {
            width = 1600.0
            height = 900.0
        }
        player.scriptProperty().addListener { _ ->
            player.rhythm.addListener(this@VlcjView)
        }
    }

    init {
        mediaPlayer.videoSurface().set(JavaFxVideoSurface())
        mediaPlayer.media().prepare(File("C:\\GeePawHillDotOrgWip\\wip\\ccs talks\\Beauty In Code 2020\\bic2020-raw-tonal.mp4").absolutePath)
        mediaPlayer.controls().setPosition(0f)
        mediaPlayer.controls().start()
        mediaPlayer.controls().pause()
        runLater {
            mediaPlayer.controls().nextFrame()
        }
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
    }

    override fun frame() {
        runLater {
            render()
        }
    }

    private fun render() {
        val g = canvas.graphicsContext2D
        if (img != null) {
            pixelBuffer!!.updateBuffer { pixBuf -> null };
            g.drawImage(img, 0.0, 0.0)
        }
    }
}
