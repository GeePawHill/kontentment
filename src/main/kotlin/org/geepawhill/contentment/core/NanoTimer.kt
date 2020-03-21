/*
 * This file is part of VLCJ.
 *
 * VLCJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VLCJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VLCJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2009, 2010, 2011, 2012, 2013, 2014, 2015 Caprica Software Limited.
 */
package org.geepawhill.contentment.core

import javafx.concurrent.ScheduledService
import javafx.concurrent.Task
import javafx.util.Duration
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory

abstract class NanoTimer(period: Double) : ScheduledService<Void>() {
    private val ONE_NANO = 1000000000L
    private val ONE_NANO_INV = 1f / 1000000000L.toDouble()
    private var startTime: Long = 0
    private var previousTime: Long = 0
    var frameRate = 0.0
        private set
    var deltaTime = 0.0
        private set
    val time: Long
        get() = System.nanoTime() - startTime

    val timeAsSeconds: Double
        get() = time * ONE_NANO_INV

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

    override fun createTask(): Task<Void> {
        return object : Task<Void>() {
            @Throws(Exception::class)
            override fun call(): Void? {
                updateTimer()
                return null
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
        onSucceeded()
    }

    override fun failed() {
        exception.printStackTrace(System.err)
        onFailed()
    }

    private inner class NanoThreadFactory : ThreadFactory {
        override fun newThread(runnable: Runnable): Thread {
            val thread = Thread(runnable, "NanoTimerThread")
            thread.priority = Thread.NORM_PRIORITY + 1
            thread.isDaemon = true
            return thread
        }
    }

    /**
     *
     */
    protected abstract fun onSucceeded()

    /**
     *
     */
    protected fun onFailed() {}

    init {
        this.period = Duration.millis(period)
        executor = Executors.newCachedThreadPool(NanoThreadFactory())
    }
}