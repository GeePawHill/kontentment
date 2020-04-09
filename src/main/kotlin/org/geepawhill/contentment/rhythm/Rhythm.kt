package org.geepawhill.contentment.rhythm

import javafx.animation.AnimationTimer
import javafx.beans.property.ReadOnlyLongProperty
import javafx.beans.property.ReadOnlyLongWrapper
import tornadofx.*
import java.time.Duration
import java.time.LocalDateTime

class Rhythm() {
    private val listeners = RhythmSyncerList()
    private val privateBeatProperty = ReadOnlyLongWrapper(0L)
    private var isPlaying = false
    private var startedPlayingAt: LocalDateTime? = null
    private var startedPauseAt: Long = 0
    private val timer: AnimationTimer

    val beatProperty: ReadOnlyLongProperty = privateBeatProperty.readOnlyProperty
    val beat: Long by beatProperty

    init {
        isPlaying = false
        startedPauseAt = 0L
        timer = object : AnimationTimer() {
            override fun handle(now: Long) {
                update()
            }
        }

    }

    fun seek(ms: Long) {
        if (isPlaying) pause()
        listeners.notify { seek(ms) }
        privateBeatProperty.set(ms)
        startedPauseAt = ms
    }

    private fun update() {
        val playerTime = if (isPlaying) startedPauseAt + Duration.between(startedPlayingAt!!, LocalDateTime.now()).toMillis() else startedPauseAt
        privateBeatProperty.set(playerTime)
    }

    fun play() {
        if (isPlaying) throw RuntimeException("Can't play when already playing.")
        listeners.notify { play() }
        startedPlayingAt = LocalDateTime.now()
        isPlaying = true
        timer.start()
    }

    fun pause() {
        if (!isPlaying) throw RuntimeException("Can't pause when not playing.")
        listeners.notify { pause() }
        timer.stop()
        startedPauseAt = beat
        isPlaying = false
    }

    fun addListener(syncer: RhythmSyncer) = listeners.add(syncer)
    fun removeListener(syncer: RhythmSyncer) = listeners.remove(syncer)

    companion object {
        const val MAX = Long.MAX_VALUE
    }
}
