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
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter
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
    private val vlcjBuffer = JavaFxBufferFormatCallback()

    fun pixelBuffer(): PixelBuffer<*> = vlcjBuffer.pixelBuffer!!
    fun image(): WritableImage = vlcjBuffer.img!!

    private val mediaPlayerFactory: MediaPlayerFactory = MediaPlayerFactory()
    val mediaPlayer: EmbeddedMediaPlayer = mediaPlayerFactory.mediaPlayers().newEmbeddedMediaPlayer()

    private class JavaFxVideoSurface(vlcjBuffer: JavaFxBufferFormatCallback) :
            CallbackVideoSurface(vlcjBuffer, JavaFxRenderCallback(), true, VideoSurfaceAdapters.getVideoSurfaceAdapter())

    private class JavaFxBufferFormatCallback : BufferFormatCallback {
        private val pixelFormat: WritablePixelFormat<ByteBuffer?> = PixelFormat.getByteBgraPreInstance()
        var pixelBuffer: PixelBuffer<*>? = null
        var img: WritableImage? = null

        private var bufferWidth = 0
        private var bufferHeight = 0

        override fun getBufferFormat(sourceWidth: Int, sourceHeight: Int): BufferFormat {
            bufferWidth = sourceWidth
            bufferHeight = sourceHeight
            return RV32BufferFormat(sourceWidth, sourceHeight)
        }

        override fun allocatedBuffers(buffers: Array<ByteBuffer>) {
            pixelBuffer = PixelBuffer(bufferWidth, bufferHeight, buffers[0], pixelFormat)
            img = WritableImage(pixelBuffer)
        }
    }

    private class JavaFxRenderCallback : RenderCallback {
        override fun display(mediaPlayer: MediaPlayer, nativeBuffers: Array<ByteBuffer>, bufferFormat: BufferFormat) {
        }
    }

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
        mediaPlayer.videoSurface().set(JavaFxVideoSurface(vlcjBuffer))
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
        val imageWidth = image().width
        val imageHeight = image().height
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

        pixelBuffer().updateBuffer { pixBuf -> null };
        g.drawImage(image(), 0.0, 0.0)
        g.transform = ax
    }
}
