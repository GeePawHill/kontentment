package org.geepawhill.contentment.rhythm

import javafx.beans.property.ReadOnlyLongProperty
import javafx.scene.media.MediaPlayer

interface Rhythm {
    val mediaPlayer: MediaPlayer?
    val beatProperty: ReadOnlyLongProperty
    val beat: Long
    fun seek(ms: Long)
    fun play()
    fun pause()
    fun addListener(syncer: RhythmSyncer)
    fun removeListener(syncer: RhythmSyncer)

    companion object {
        const val MAX = java.lang.Long.MAX_VALUE
    }
}