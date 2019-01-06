package org.geepawhill.contentment.player

import javafx.animation.Transition
import javafx.util.Duration
import org.geepawhill.contentment.core.Context
import org.geepawhill.contentment.core.Gesture
import org.geepawhill.contentment.core.OnFinished

import java.util.function.Supplier

class BeatWaiter(private val context: Context, private val step: Gesture, private val isDone: Supplier<Boolean>, private val onFinished: OnFinished) {
    private var animator: Transition? = null

    fun play() {
        step.slow(context, object : OnFinished {
            override fun run() {
                stepFinished()
            }
        })
    }

    fun stepFinished() {
        animator = object : Transition() {
            init {
                cycleDuration = Duration.INDEFINITE
            }

            override fun interpolate(frac: Double) {
                if (isDone.get()) {
                    finishAndDie()
                }
            }

        }
        animator!!.play()
    }

    private fun finishAndDie() {
        animator!!.stop()
        onFinished.run()
    }
}