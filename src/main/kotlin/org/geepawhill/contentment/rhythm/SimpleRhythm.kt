package org.geepawhill.contentment.rhythm

import java.time.*

import javafx.animation.AnimationTimer
import javafx.beans.property.*
import javafx.scene.media.MediaPlayer

class SimpleRhythm : Rhythm {
    private val beatProperty: SimpleLongProperty = SimpleLongProperty(0L)
    override var isPlaying: Boolean = false
    private var startedPlayingAt: LocalDateTime? = null
    private var startedPauseAt: Long = 0

    private val timer: AnimationTimer

    private val playerTime: Long
        get() = if (isPlaying) startedPauseAt + Duration.between(startedPlayingAt!!, LocalDateTime.now()).toMillis() else startedPauseAt

    override val isAtEnd: Boolean
        get() = true

    override val mediaPlayer: MediaPlayer?
        get() = null

    init {
        isPlaying = false
        startedPauseAt = 0L
        timer = object : AnimationTimer() {

            override fun handle(now: Long) {
                update()

            }
        }
    }

    override fun beatProperty(): LongProperty {
        return beatProperty
    }

    override fun beat(): Long {
        update()
        return beatProperty.get()
    }

    override fun seekHard(ms: Long) {
        if (isPlaying) pause()
        beatProperty.set(ms)
        startedPauseAt = ms
    }

    override fun update() {
        val newBeat = playerTime
        beatProperty.set(newBeat)
    }

    override fun play() {
        if (isPlaying) throw RuntimeException("Can't play when already playing.")
        startedPlayingAt = LocalDateTime.now()
        isPlaying = true
        timer.start()
    }

    override fun pause() {
        if (!isPlaying) throw RuntimeException("Can't pause when not playing.")
        timer.stop()
        startedPauseAt = beat()
        isPlaying = false
    }

}
