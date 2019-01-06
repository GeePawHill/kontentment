package org.geepawhill.contentment.test

import javafx.animation.Transition
import javafx.application.Platform
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.stage.Stage
import org.geepawhill.contentment.core.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

class JavaFxRunner internal constructor() {
    var context: Context

    init {
        context = Context()
    }

    fun prepareWindow(stage: Stage) {
        val region = Pane()
        region.setMaxSize(1600.0, 900.0)
        region.setMinSize(1600.0, 900.0)
        region.children.add(context.canvas)
        stage.scene = Scene(region)
        stage.show()
    }

    fun play(transition: Transition) {
        val action = Consumer<CountDownLatch> { latch ->
            transition.setOnFinished { event -> latch.countDown() }
            transition.play()
        }
        actLater(action)

    }

    class CountDownOnFinish(val latch: CountDownLatch) : OnFinished {
        override fun run() {
            latch.countDown()
        }
    }

    fun slow(step: Gesture) {
        val action = Consumer<CountDownLatch> { latch -> step.slow(context, CountDownOnFinish(latch)) }
        actLater(action)
    }

    fun fast(step: Gesture) {
        val action = Consumer<CountDownLatch> { latch ->
            step.fast(context)
            latch.countDown()
        }
        actLater(action)
    }

    fun actLater(action: Consumer<CountDownLatch>) {
        val latch = CountDownLatch(1)
        Platform.runLater { action.accept(latch) }
        waitForlatch(latch)
    }

    private fun waitForlatch(latch: CountDownLatch) {
        try {
            val finished = latch.await(5000, TimeUnit.MILLISECONDS)
            if (!finished) throw RuntimeException("Wait timed out.")
        } catch (e: InterruptedException) {
            throw RuntimeException(e)
        }

    }

    fun play(ms: Long, atom: Fragment) {
        val action = Consumer<CountDownLatch> { latch -> FragmentTransition(ms, atom, context, CountDownOnFinish(latch)).play() }
        actLater(action)
    }
}