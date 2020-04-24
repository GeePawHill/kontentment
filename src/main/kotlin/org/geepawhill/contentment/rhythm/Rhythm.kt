package org.geepawhill.contentment.rhythm

import javafx.beans.property.ReadOnlyLongProperty
import javafx.beans.property.ReadOnlyLongWrapper
import tornadofx.*
import java.time.Duration
import java.time.LocalDateTime


class Rhythm() {

    private val listeners = RhythmListeners()
    private val privateBeatProperty = ReadOnlyLongWrapper(0L)
    private var isPlaying = false
    private var startedPlayingAt: LocalDateTime = LocalDateTime.now()
    private var startedPauseAt: Long = 0
    private val timer = FpsTimer(60) { update() }

    val beatProperty: ReadOnlyLongProperty = privateBeatProperty.readOnlyProperty
    val beat: Long by beatProperty

    fun seek(ms: Long) {
        if (isPlaying) pause()
        listeners.notify { seek(ms) }
        privateBeatProperty.set(ms)
        startedPauseAt = ms
    }

    private fun update() {
        if (isPlaying) {
            privateBeatProperty.set(startedPauseAt + millisSincePlay())
            listeners.notify { frame() }
        } else privateBeatProperty.set(startedPauseAt)
    }

    private fun millisSincePlay() = Duration.between(startedPlayingAt, LocalDateTime.now()).toMillis()

    fun play() {
        if (isPlaying) throw RuntimeException("Can't play when already playing.")
        listeners.notify { play() }
        startedPlayingAt = LocalDateTime.now()
        isPlaying = true
        timer.play()
    }

    fun pause() {
        if (!isPlaying) throw RuntimeException("Can't pause when not playing.")
        listeners.notify { pause() }
        startedPauseAt = beat
        isPlaying = false
        timer.pause()
    }

    fun addListener(listener: RhythmListener) = listeners.add(listener)
    fun removeListener(listener: RhythmListener) = listeners.remove(listener)

    companion object {
        const val MAX = Long.MAX_VALUE
    }
}
