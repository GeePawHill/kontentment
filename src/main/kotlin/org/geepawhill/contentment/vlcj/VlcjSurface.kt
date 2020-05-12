package org.geepawhill.contentment.vlcj

import javafx.scene.image.PixelBuffer
import javafx.scene.image.PixelFormat
import javafx.scene.image.WritableImage
import javafx.scene.image.WritablePixelFormat
import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer
import uk.co.caprica.vlcj.player.embedded.videosurface.CallbackVideoSurface
import uk.co.caprica.vlcj.player.embedded.videosurface.VideoSurfaceAdapters
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.BufferFormat
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.BufferFormatCallback
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.RenderCallback
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.format.RV32BufferFormat
import java.nio.ByteBuffer

/**
 * An instance of a VLCJ BufferFormatCallback.
 * This class is used to allow VLCJ to put its frames into a JavaFx WritableImage.
 * It must be attached to an EmbeddedMediaPlayer instance.
 * The update method causes VLCJ to change the image from its current frame.
 */
class VlcjSurface : BufferFormatCallback {
    private class NoOpRender : RenderCallback {
        override fun display(mediaPlayer: MediaPlayer, nativeBuffers: Array<ByteBuffer>, bufferFormat: BufferFormat) {
        }
    }

    private val format: WritablePixelFormat<ByteBuffer> = PixelFormat.getByteBgraPreInstance()
    private var width = INITIAL_WIDTH
    private var height = INITIAL_HEIGHT
    private var buffer: PixelBuffer<ByteBuffer> = PixelBuffer(width, height, ByteBuffer.allocateDirect(INITIAL_BYTES), format)

    var image: WritableImage = WritableImage(buffer)

    override fun getBufferFormat(sourceWidth: Int, sourceHeight: Int): BufferFormat {
        width = sourceWidth
        height = sourceHeight
        return RV32BufferFormat(sourceWidth, sourceHeight)
    }

    override fun allocatedBuffers(buffers: Array<ByteBuffer>) {
        buffer = PixelBuffer(width, height, buffers[0], format)
        image = WritableImage(buffer)
    }

    fun update() {
        buffer.updateBuffer { _ -> null };
    }

    fun attach(mediaPlayer: EmbeddedMediaPlayer) {
        val surface = CallbackVideoSurface(
                this,
                NoOpRender(),
                true,
                VideoSurfaceAdapters.getVideoSurfaceAdapter()
        )
        mediaPlayer.videoSurface().set(surface)
    }

    companion object {
        private const val INITIAL_WIDTH = 16
        private const val INITIAL_HEIGHT = 9
        private const val INITIAL_BYTES = (INITIAL_WIDTH * INITIAL_HEIGHT * 4)
    }
}
