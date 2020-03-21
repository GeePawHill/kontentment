package org.geepawhill.contentment.rhythm

import javafx.animation.AnimationTimer
import javafx.beans.property.LongProperty
import javafx.beans.property.SimpleLongProperty
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import java.io.File
import java.time.Duration
import java.time.LocalDateTime

class MediaRhythm(mediaString: String) : Rhythm {
    private val beatProperty = SimpleLongProperty(0L)
    override var isPlaying: Boolean = false
    private var startedPlayingAt: LocalDateTime? = null
    private var startedPauseAt: Long = 0

    private val timer: AnimationTimer
    override val mediaPlayer: MediaPlayer?

    private val playerTime: Long
        get() = if (isPlaying) startedPauseAt + Duration.between(startedPlayingAt!!, LocalDateTime.now()).toMillis() else startedPauseAt

    override val isAtEnd: Boolean
        get() = mediaPlayer!!.getCurrentTime() == mediaPlayer.getCycleDuration()

    @JvmOverloads
    constructor(file: File = File("/01faceoverCut.mp4")) : this(file.toURI().toString()) {
    }

    init {
        val m = Media(mediaString)
        mediaPlayer = MediaPlayer(m)
        mediaPlayer.pause()
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
        if (ms == Rhythm.MAX) {
            mediaPlayer!!.seek(javafx.util.Duration.millis(mediaPlayer.getTotalDuration().toMillis() - 50.0))
        } else {
            mediaPlayer!!.seek(javafx.util.Duration.millis(ms.toDouble()))
        }
        beatProperty.set(ms)
        startedPauseAt = ms
    }

    override fun update() {
        val newBeat = playerTime
        beatProperty.set(newBeat)
    }

    override fun play() {
        if (isPlaying) throw RuntimeException("Can't play when already playing.")
        mediaPlayer!!.play()
        startedPlayingAt = LocalDateTime.now()
        isPlaying = true
        timer.start()
    }

    override fun pause() {
        if (!isPlaying) throw RuntimeException("Can't pause when not playing.")
        mediaPlayer!!.pause()
        timer.stop()
        startedPauseAt = beat()
        isPlaying = false
    }

}
