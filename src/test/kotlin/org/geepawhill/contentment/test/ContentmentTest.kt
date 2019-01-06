package org.geepawhill.contentment.test

import java.util.concurrent.CountDownLatch

import javax.swing.SwingUtilities

import org.geepawhill.contentment.core.Context

import javafx.embed.swing.JFXPanel

open class ContentmentTest {
    val runner = JavaFxRunner()

    val context: Context
        get() = runner.context

    init {
        runIfNeeded()
    }

    private fun runIfNeeded() {
        try {
            runJavaFx()
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }

    }

    @Throws(InterruptedException::class)
    protected fun runJavaFx() {
        if (javaFxRunning) return
        val timeMillis = System.currentTimeMillis()
        val latch = CountDownLatch(1)
        SwingUtilities.invokeLater(object : Runnable {
            override fun run() {
                forceJavaFxStart()
                latch.countDown()
            }

            private fun forceJavaFxStart() {
                JFXPanel()
            }
        })

        latch.await()
        javaFxRunning = true
    }

    companion object {
        private var javaFxRunning: Boolean = false
    }

}
