package org.geepawhill.contentment.rhythm

import javafx.concurrent.ScheduledService
import javafx.concurrent.Task
import javafx.util.Duration
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory

class NanoTimer(millis: Double, val pulse: () -> Unit) : ScheduledService<Unit>() {
    private var startTime = 0L
    private var previousTime = 0L
    private var frameRate = 0.0
    private var deltaTime = 0.0
    val time: Long
        get() = System.nanoTime() - startTime

    val timeAsSeconds: Double
        get() = time * ONE_NANO_INV

    init {
        this.period = Duration.millis(millis)
        executor = Executors.newCachedThreadPool(NanoThreadFactory())
    }

    override fun start() {
        super.start()
        if (startTime <= 0) {
            startTime = System.nanoTime()
        }
    }

    override fun reset() {
        super.reset()
        startTime = System.nanoTime()
        previousTime = time
    }

    override fun createTask(): Task<Unit> {
        return object : Task<Unit>() {
            @Throws(Exception::class)
            override fun call() {
                updateTimer()
            }
        }
    }

    private fun updateTimer() {
        deltaTime = (time - previousTime) * (1.0f / ONE_NANO).toDouble()
        frameRate = 1.0f / deltaTime
        previousTime = time
    }

    override fun succeeded() {
        super.succeeded()
        pulse()
    }

    override fun failed() {
        exception.printStackTrace(System.err)
    }

    private inner class NanoThreadFactory : ThreadFactory {
        override fun newThread(runnable: Runnable): Thread {
            val thread = Thread(runnable, "NanoTimerThread")
            thread.priority = Thread.NORM_PRIORITY + 1
            thread.isDaemon = true
            return thread
        }
    }

    companion object {
        private const val ONE_NANO = 1000000000L
        private const val ONE_NANO_INV = 1f / 1000000000L.toDouble()
    }
}