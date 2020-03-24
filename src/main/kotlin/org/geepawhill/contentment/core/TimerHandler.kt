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

    override fun playing(mediaPlayer: MediaPlayer) {
        application.startTimer()
    }

    override fun paused(mediaPlayer: MediaPlayer) {
        application.pauseTimer()
    }

    override fun stopped(mediaPlayer: MediaPlayer) {
        application.stopTimer()
    }

    override fun finished(mediaPlayer: MediaPlayer) {
        application.stopTimer()
    }

    override fun error(mediaPlayer: MediaPlayer) {
        application.stopTimer()
    }

}