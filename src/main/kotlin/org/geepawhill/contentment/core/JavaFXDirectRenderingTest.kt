/*
 * This file is part of VLCJ.
 *
 * VLCJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VLCJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VLCJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2009, 2010, 2011, 2012, 2013, 2014, 2015 Caprica Software Limited.
 */
package org.geepawhill.contentment.core

import javafx.animation.Interpolator
import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.application.Application
import javafx.application.Platform
import javafx.beans.Observable
import javafx.beans.property.DoubleProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.control.Alert
import javafx.scene.control.Label
import javafx.scene.control.MenuBar
import javafx.scene.image.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.scene.text.TextAlignment
import javafx.stage.*
import javafx.util.Duration
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

class JavaFXDirectRenderingTest : Application() {
    private val pixelFormat: WritablePixelFormat<ByteBuffer?> = PixelFormat.getByteBgraPreInstance()
    private val mediaPlayerFactory: MediaPlayerFactory = MediaPlayerFactory()
    val mediaPlayer: EmbeddedMediaPlayer = mediaPlayerFactory.mediaPlayers().newEmbeddedMediaPlayer()

    private val borderPane: BorderPane = BorderPane()
    private val stackPane = StackPane()
    private val canvas: Canvas = Canvas()
    private val canvasPane: Pane = Pane()
    private val imageView: ImageView = ImageView(Image(javaClass.getResourceAsStream("/vlcj-logo.png")))

    private val fileChooser: FileChooser = FileChooser()

    private var stage: Stage? = null
    private var videoControlsStage: Stage? = null

    private var scene: Scene? = null
    private val menuBar: MenuBar = MenuBuilder.createMenu(this)
    private val controlsPane = ControlsPane(mediaPlayer)
    private var bufferWidth = 0
    private var bufferHeight = 0

    private var pixelBuffer: PixelBuffer<*>? = null
    private var img: WritableImage? = null
    private var showStats = true
    private var showAnimation = true
    private var start: Long = 0
    private var frames: Long = 0
    private var maxFrameTime: Long = 0
    private var totalFrameTime: Long = 0
    private val x: DoubleProperty = SimpleDoubleProperty()
    private val y: DoubleProperty = SimpleDoubleProperty()
    private val opacity: DoubleProperty = SimpleDoubleProperty()
    private val cursorHandler: CursorHandler

    private val nanoTimer = NanoTimer(1000.0 / FPS) {
        renderFrame()
    }

    init {
        mediaPlayer.events().addMediaPlayerEventListener(TimerHandler(this))
        mediaPlayer.videoSurface().set(JavaFxVideoSurface())
        borderPane.style = BLACK_BACKGROUND_STYLE
        canvas.style = BLACK_BACKGROUND_STYLE
        canvasPane.style = BLACK_BACKGROUND_STYLE
        canvasPane.children.add(canvas)
        canvas.widthProperty().bind(canvasPane.widthProperty())
        canvas.heightProperty().bind(canvasPane.heightProperty())

        // Listen to width/height changes to force the video surface to re-render if the media player is not currently
        // playing - this is necessary to repaint damaged regions because the repaint timer is stopped/paused while the
        // media player is not playing
        canvas.widthProperty().addListener { event: Observable? -> if (!mediaPlayer.status().isPlaying) renderFrame() }
        canvas.heightProperty().addListener { event: Observable? -> if (!mediaPlayer.status().isPlaying) renderFrame() }
        stackPane.children.addAll(canvasPane, imageView)
        val statusPane = Pane()
        borderPane.center = stackPane
        statusPane.style = STATUS_BACKGROUND_STYLE
        val statusLabel = Label("vlcj-javafx with PixelBuffer is ready for awesome")
        statusLabel.style = STATUS_BACKGROUND_STYLE
        statusPane.children.add(statusLabel)
        borderPane.bottom = statusPane
        borderPane.bottom = controlsPane
        borderPane.top = menuBar
        fileChooser.title = "Open Media File"
        fileChooser.initialDirectory = File(System.getProperty("user.home"))
        fileChooser.extensionFilters.addAll(
                FileChooser.ExtensionFilter("Media Files", "*.avi", "*.flv", "*.mp4", "*.mpeg", "*.mpg", ".wmv"),
                FileChooser.ExtensionFilter("All Files", "*.*")
        )
        mediaPlayer.events().addMediaPlayerEventListener(object : MediaPlayerEventAdapter() {
            override fun finished(mediaPlayer: MediaPlayer) {
                if (!mediaPlayer.controls().repeat) {
                    showVideo(false)
                }
            }

            override fun error(mediaPlayer: MediaPlayer) {
                showVideo(false)
            }

            override fun playing(mediaPlayer: MediaPlayer) {
                showVideo(true)
                // Reset the frame stats each time the media is started (otherwise e.g. a pause would mess with the
                // stats (like FPS)
                resetStats()
            }
        })
        val timeline = Timeline(
                KeyFrame(Duration.seconds(0.0),
                        KeyValue(x, 10, Interpolator.EASE_BOTH),
                        KeyValue(y, 10)
                ),
                KeyFrame(Duration.seconds(0.5),
                        KeyValue(x, 70, Interpolator.EASE_BOTH),
                        KeyValue(y, 10)
                )
        )
        timeline.isAutoReverse = true
        timeline.cycleCount = Timeline.INDEFINITE
        val timeline2 = Timeline(
                KeyFrame(Duration.seconds(0.0),
                        KeyValue(opacity, 0, Interpolator.EASE_BOTH)
                ),
                KeyFrame(Duration.seconds(0.5),
                        KeyValue(opacity, 1, Interpolator.EASE_BOTH)
                )
        )
        timeline2.isAutoReverse = true
        timeline2.cycleCount = Timeline.INDEFINITE
        timeline.play()
        timeline2.play()
        cursorHandler = CursorHandler(canvas, MOUSE_TIMEOUT)
        cursorHandler.start()
    }

    override fun start(primaryStage: Stage) {
        stage = primaryStage
        stage!!.title = "vlcj JavaFX PixelBuffer test"
        stage!!.x = 400.0
        stage!!.y = 200.0
        stage!!.width = 900.0
        stage!!.height = 600.0
        scene = Scene(borderPane, Color.BLACK)
        stage!!.onCloseRequest = EventHandler { windowEvent: WindowEvent? -> System.exit(0) }
        videoControlsStage = Stage(StageStyle.UNDECORATED)
        videoControlsStage!!.title = "Video Adjustments"
        videoControlsStage!!.scene = Scene(VideoControlsPane(mediaPlayer), Color.BLACK)
        videoControlsStage!!.onShowing = EventHandler { windowEvent: WindowEvent? ->
            videoControlsStage!!.x = stage!!.x + stage!!.width + 4
            videoControlsStage!!.y = stage!!.y
        }
        videoControlsStage!!.sizeToScene()
        primaryStage.scene = scene
        primaryStage.show()
        mediaPlayer.controls().repeat = true
        startTimer()
    }

    override fun stop() {
        stopTimer()
        mediaPlayer.controls().stop()
        mediaPlayer.release()
        mediaPlayerFactory.release()
    }

    private inner class JavaFxVideoSurface internal constructor() : CallbackVideoSurface(JavaFxBufferFormatCallback(), JavaFxRenderCallback(), true, VideoSurfaceAdapters.getVideoSurfaceAdapter())

    private inner class JavaFxBufferFormatCallback : BufferFormatCallback {
        override fun getBufferFormat(sourceWidth: Int, sourceHeight: Int): BufferFormat {
            bufferWidth = sourceWidth
            bufferHeight = sourceHeight

            // This does not need to be done here, but you could set the video surface size to match the native video
            // size
            return RV32BufferFormat(sourceWidth, sourceHeight)
        }

        override fun allocatedBuffers(buffers: Array<ByteBuffer>) {
            // This is the new magic sauce, the native video buffer is used directly for the image buffer - there is no
            // full-frame buffer copy here
            pixelBuffer = PixelBuffer(bufferWidth, bufferHeight, buffers[0], pixelFormat)
            img = WritableImage(pixelBuffer)
        }
    }

    // This is correct as far as it goes, but we need to use one of the timers to get smooth rendering (the timer is
    // handled by the demo sub-classes)
    private inner class JavaFxRenderCallback : RenderCallback {
        override fun display(mediaPlayer: MediaPlayer, nativeBuffers: Array<ByteBuffer>, bufferFormat: BufferFormat) {
//            Platform.runLater {
//                pixelBuffer!!.updateBuffer { pixBuf: Any? -> null }
//            }
        }
    }

    /**
     * This method is called for each tick of whatever timer implementation has been chosen..
     *
     *
     * Needless to say, this method should run as quickly as possible.
     */
    protected fun renderFrame() {
        frames++
        val renderStart = System.currentTimeMillis()
        val g = canvas.graphicsContext2D
        val width = canvas.width
        val height = canvas.height

        // The canvas must always be filled with background colour first since the rendered image may actually be
        // smaller than the full canvas - otherwise we will end up with garbage in the borders on resize
        g.fill = Color(0.0, 0.0, 0.0, 0.0)
        g.fillRect(0.0, 0.0, width, height)
        if (img != null) {
            val imageWidth = img!!.width
            val imageHeight = img!!.height
            val sx = width / imageWidth
            val sy = height / imageHeight
            val sf = Math.min(sx, sy)
            val scaledW = imageWidth * sf
            val scaledH = imageHeight * sf
            val ax = g.transform
            g.translate(
                    (width - scaledW) / 2,
                    (height - scaledH) / 2
            )
            if (sf != 1.0) {
                g.scale(sf, sf)
            }

            pixelBuffer!!.updateBuffer { pixBuf -> null };
            g.drawImage(img, 0.0, 0.0)
            val fps = 1000.toDouble() * frames / (renderStart - start)
            val meanFrameTime = totalFrameTime / frames.toDouble()
            if (showStats) {
                val `val` = String.format(
                        """ Frames: %d
Seconds: %d
    FPS: %01.1f
Maximum: %d ms
   Mean: %01.3f ms""",
                        frames, (renderStart - start) / 1000, fps, maxFrameTime, meanFrameTime
                )
                renderText(g, `val`, 100.0, 200.0)
            }
            if (showAnimation) {
                g.fill = Color.CORNSILK
                g.fillOval(
                        x.doubleValue(),
                        y.doubleValue(), 40.0, 40.0)
                g.save()
                g.globalAlpha = opacity.doubleValue()
                g.textAlign = TextAlignment.CENTER
                renderText(g, "vlcj JavaFX PixelBuffer Win!", img!!.width / 2, img!!.height - 120)
                g.restore()
            }
            g.transform = ax
        }
        if (renderStart - start > 1000) {
            val renderTime = System.currentTimeMillis() - renderStart
            maxFrameTime = Math.max(maxFrameTime, renderTime)
            totalFrameTime += renderTime
        }
    }

    /**
     * A crude, but fast, renderer to draw outlined text.
     *
     *
     * Generally the approach here is faster than getting the text outline and stroking it.
     *
     * @param g
     * @param text
     * @param x
     * @param y
     */
    private fun renderText(g: GraphicsContext, text: String, x: Double, y: Double) {
        g.font = FONT
        g.fill = BLACK
        g.fillText(text, x - 1, y - 1)
        g.fillText(text, x + 1, y - 1)
        g.fillText(text, x - 1, y + 1)
        g.fillText(text, x + 1, y + 1)
        g.fill = WHITE
        g.fillText(text, x, y)
    }

    private fun resetStats() {
        start = System.currentTimeMillis()
        frames = 0
        maxFrameTime = 0
        totalFrameTime = 0
    }

    fun openFile() {
        val selectedFile = fileChooser.showOpenDialog(stage)
        if (selectedFile != null) {
            mediaPlayer.media().play(selectedFile.absolutePath)
        }
    }

    fun adjustVideo(selected: Boolean) {
        if (selected) {
            videoControlsStage!!.show()
            mediaPlayer.video().isAdjustVideo = true
        } else {
            videoControlsStage!!.hide()
            mediaPlayer.video().isAdjustVideo = false
        }
    }

    fun toggleAlwaysOnTop() {
        stage!!.isAlwaysOnTop = !stage!!.isAlwaysOnTop
    }

    fun toggleMinimalInterface(on: Boolean) {
        menuBar.isVisible = on
        controlsPane.isVisible = on

        // Also need to set the managed property to prevent hidden components taking layout space
        menuBar.isManaged = on
        controlsPane.isManaged = on
    }

    fun toggleFullScreen() {
        stage!!.isFullScreen = !stage!!.isFullScreen
    }

    fun toggleStatsOverlay(show: Boolean) {
        showStats = show
    }

    fun toggleAnimationOverlay(show: Boolean) {
        showAnimation = show
    }

    fun showAbout() {
        val alert = Alert(Alert.AlertType.INFORMATION)
        alert.title = "About vlcj JavaFX"
        alert.headerText = null
        alert.contentText = """
            This demo shows how to use vlcj with JavaFX and the new proposed JavaFX PixelBuffer class.

            PixelBuffer allows us to share the native video buffer directly which means we can reduce the number of full-frame copies for nice performance!

            This means LibVLC renders *directly* into the buffer used to render the frame image in a JavaFX canvas.

            If you're interested in vlcj and JavaFX you should lobby hard for next version of JavaFX to include PixelBuffer!
            """.trimIndent()
        alert.initOwner(stage)
        alert.initModality(Modality.APPLICATION_MODAL)
        alert.showAndWait()
    }

    private fun showVideo(show: Boolean) {
        Platform.runLater {
            canvasPane.isVisible = show
            imageView.isVisible = !show
        }
    }


    /**
     *
     */
    fun startTimer() {
        Platform.runLater {
            if (!nanoTimer.isRunning) {
                nanoTimer.reset()
                nanoTimer.start()
            }
        }
    }

    fun pauseTimer() {
        Platform.runLater {
            if (nanoTimer.isRunning) {
                nanoTimer.cancel()
            }
        }
    }

    fun stopTimer() {
        Platform.runLater {
            if (nanoTimer.isRunning) {
                nanoTimer.cancel()
            }
        }
    }

    companion object {
        private const val BLACK_BACKGROUND_STYLE = "-fx-background-color: rgb(0, 0, 0);"
        private const val STATUS_BACKGROUND_STYLE = "-fx-background-color: rgb(232, 232, 232); -fx-label-padding: 8 8 8 8;"
        private val BLACK = Color(0.0, 0.0, 0.0, 1.0)
        private val WHITE = Color(1.0, 1.0, 1.0, 1.0)
        private val FONT = Font.font("Monospace", 40.0)

        /**
         * Mouse pointer will be hidden when over the video surface after this inactivity timeout (milliseconds).
         */
        private const val MOUSE_TIMEOUT: Long = 3000

        /**
         *
         */
        private const val FPS = 60.0

        /**
         * Application entry point.
         *
         * @param args command-line arguments
         */
        @JvmStatic
        fun main(args: Array<String>) //static method
        {
            Application.launch(JavaFXDirectRenderingTest::class.java)
        }
    }
}