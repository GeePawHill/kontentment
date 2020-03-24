package org.geepawhill.contentment.core

import javafx.application.Platform
import uk.co.caprica.vlcj.player.base.MediaPlayer
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter

class TimerHandler(private val timer: NanoTimer) : MediaPlayerEventAdapter() {

    override fun playing(mediaPlayer: MediaPlayer) {
        Platform.runLater {
            if (!timer.isRunning) {
                timer.reset()
                timer.start()
            }
        }
    }

    override fun paused(mediaPlayer: MediaPlayer) {
        Platform.runLater {
            if (timer.isRunning) {
                timer.cancel()
            }
        }
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

    private fun stopTimer() {
        Platform.runLater {
            if (timer.isRunning) {
                timer.cancel()
            }
        }
    }

}