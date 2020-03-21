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

import javafx.application.Application
import javafx.application.Platform

/**
 * Implementation of a JavaFX direct rendering media player that uses the [NanoTimer].
 *
 *
 * In principle, this should be better performing than the corresponding JavaFX Timeline example.
 */
class NanoTimerJavaFXDirectRenderingTest : JavaFXDirectRenderingTest() {
    /**
     *
     */
    private val nanoTimer: NanoTimer = object : NanoTimer(1000.0 / FPS) {
        override fun onSucceeded() {
            renderFrame()
        }
    }

    override fun startTimer() {
        Platform.runLater {
            if (!nanoTimer.isRunning) {
                nanoTimer.reset()
                nanoTimer.start()
            }
        }
    }

    override fun pauseTimer() {
        Platform.runLater {
            if (nanoTimer.isRunning) {
                nanoTimer.cancel()
            }
        }
    }

    override fun stopTimer() {
        Platform.runLater {
            if (nanoTimer.isRunning) {
                nanoTimer.cancel()
            }
        }
    }

    companion object {
        /**
         *
         */
        private const val FPS = 60.0

        /**
         * Application entry point.
         *
         * @param args command-line arguments
         */
        @JvmStatic
        fun main(args: Array<String>) //static method
        {
            Application.launch(NanoTimerJavaFXDirectRenderingTest::class.java)
        }
    }
}