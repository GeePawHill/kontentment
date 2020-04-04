package org.geepawhill.contentment.rhythm

import javafx.beans.property.ReadOnlyLongProperty
import javafx.scene.media.MediaPlayer

interface Rhythm {
    val isAtEnd: Boolean
    val mediaPlayer: MediaPlayer?
    val beatProperty: ReadOnlyLongProperty
    val beat: Long
    fun seek(ms: Long)
    fun play()
    fun pause()
    fun addListener(listener: AnalogRhythmListener)
    fun removeListener(listener: AnalogRhythmListener)

    companion object {
        const val MAX = java.lang.Long.MAX_VALUE
    }
}