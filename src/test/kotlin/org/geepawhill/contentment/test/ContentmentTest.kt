package org.geepawhill.contentment.test

import javafx.embed.swing.JFXPanel
import org.geepawhill.contentment.core.Context
import java.util.concurrent.CountDownLatch
import javax.swing.SwingUtilities

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
        println("Running JavaFx")
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
        println("Now running.")
    }

    companion object {
        private var javaFxRunning: Boolean = false
    }

}
