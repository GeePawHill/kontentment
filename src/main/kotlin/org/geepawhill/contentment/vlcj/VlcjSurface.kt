package org.geepawhill.contentment.vlcj

import javafx.scene.image.PixelBuffer
import javafx.scene.image.PixelFormat
import javafx.scene.image.WritableImage
import javafx.scene.image.WritablePixelFormat
import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.embedded.videosurface.CallbackVideoSurface
import uk.co.caprica.vlcj.player.embedded.videosurface.VideoSurfaceAdapters
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.BufferFormat
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.BufferFormatCallback
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.RenderCallback
import uk.co.caprica.vlcj.player.embedded.videosurface.callback.format.RV32BufferFormat
import java.nio.ByteBuffer

class VlcjSurface(private val writer: VlcjWriter = VlcjWriter()) :
        CallbackVideoSurface(
                writer,
                NoOpRender(),
                true,
                VideoSurfaceAdapters.getVideoSurfaceAdapter()
        ) {

    val image get() = writer.image
    fun update() = writer.update()
}

private class NoOpRender : RenderCallback {
    override fun display(mediaPlayer: MediaPlayer, nativeBuffers: Array<ByteBuffer>, bufferFormat: BufferFormat) {
    }
}

class VlcjWriter : BufferFormatCallback {
    private val pixelFormat: WritablePixelFormat<ByteBuffer> = PixelFormat.getByteBgraPreInstance()
    private var bufferWidth = 16
    private var bufferHeight = 9
    var pixelBuffer: PixelBuffer<ByteBuffer> = PixelBuffer(bufferWidth, bufferHeight, ByteBuffer.allocateDirect(16 * 9 * 4), pixelFormat)
    var image: WritableImage = WritableImage(pixelBuffer)

    override fun getBufferFormat(sourceWidth: Int, sourceHeight: Int): BufferFormat {
        bufferWidth = sourceWidth
        bufferHeight = sourceHeight
        return RV32BufferFormat(sourceWidth, sourceHeight)
    }

    override fun allocatedBuffers(buffers: Array<ByteBuffer>) {
        pixelBuffer = PixelBuffer(bufferWidth, bufferHeight, buffers[0], pixelFormat)
        image = WritableImage(pixelBuffer)
    }

    fun update() {
        pixelBuffer.updateBuffer { _ -> null };
    }
}
