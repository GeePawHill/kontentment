package org.geepawhill.contentment.rhythm

import java.util.*

class FpsTimer(fps: Int, private val pulse: () -> Unit) {
    private val period = 1000L / fps
    private var timer = Timer("FpsTimer", true)

    private class FpsTask(private val pulse: () -> Unit) : TimerTask() {
        override fun run() {
            pulse()
        }
    }

    fun play() {
        timer.scheduleAtFixedRate(FpsTask { pulse() }, 0, period)
    }

    fun pause() {
        timer.cancel()
        timer = Timer("FpsTimer", true)
    }
}