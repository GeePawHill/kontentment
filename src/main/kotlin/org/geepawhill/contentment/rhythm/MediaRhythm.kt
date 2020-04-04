package org.geepawhill.contentment.rhythm

import javafx.animation.AnimationTimer
import javafx.beans.property.ReadOnlyLongProperty
import javafx.beans.property.ReadOnlyLongWrapper
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import tornadofx.*
import java.io.File
import java.time.Duration
import java.time.LocalDateTime

class MediaRhythm(mediaString: String) : Rhythm {
    private val privateBeatProperty = ReadOnlyLongWrapper(0L)
    override val beatProperty: ReadOnlyLongProperty = privateBeatProperty.readOnlyProperty
    override val mediaPlayer: MediaPlayer?
    override val beat: Long by beatProperty

    private var isPlaying = false
    private var startedPlayingAt: LocalDateTime? = null
    private var startedPauseAt: Long = 0

    private val timer: AnimationTimer

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

    override fun seekHard(ms: Long) {
        if (isPlaying) pause()
        if (ms == Rhythm.MAX) {
            mediaPlayer!!.seek(javafx.util.Duration.millis(mediaPlayer.getTotalDuration().toMillis() - 50.0))
        } else {
            mediaPlayer!!.seek(javafx.util.Duration.millis(ms.toDouble()))
        }
        privateBeatProperty.set(ms)
        startedPauseAt = ms
    }

    private fun update() {
        val newBeat = playerTime
        privateBeatProperty.set(newBeat)
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
        startedPauseAt = beat
        isPlaying = false
    }

}
