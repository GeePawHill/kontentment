package org.geepawhill.contentment.rhythm

import javafx.beans.property.LongProperty
import javafx.scene.media.MediaPlayer

interface Rhythm {
    val isAtEnd: Boolean
    val mediaPlayer: MediaPlayer?

    fun beatProperty(): LongProperty
    fun beat(): Long
    fun seekHard(ms: Long)
    fun play()
    fun pause()

    companion object {
        val MAX = java.lang.Long.MAX_VALUE
    }
}