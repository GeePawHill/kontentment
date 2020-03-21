package org.geepawhill.contentment.core

import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter

/**
 * A media player event listener dedicated to managing the repaint timer.
 *
 *
 * No need to consume CPU if paused/stopped.
 */
internal class TimerHandler(private val application: JavaFXDirectRenderingTest) : MediaPlayerEventAdapter() {
    private fun startTimer() {
        application.startTimer()
    }

    private fun pauseTimer() {
        application.pauseTimer()
    }

    private fun stopTimer() {
        application.stopTimer()
    }

    override fun playing(mediaPlayer: MediaPlayer) {
        startTimer()
    }

    override fun paused(mediaPlayer: MediaPlayer) {
        pauseTimer()
    }

    override fun stopped(mediaPlayer: MediaPlayer) {
        stopTimer()
    }

    override fun finished(mediaPlayer: MediaPlayer) {
        stopTimer()
    }

    override fun error(mediaPlayer: MediaPlayer) {
        stopTimer()
    }

}