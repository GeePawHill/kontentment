package org.geepawhill.contentment.step

import org.geepawhill.contentment.core.Context
import org.geepawhill.contentment.core.Gesture
import org.geepawhill.contentment.core.OnFinished

/**
 * Handy gesture for testing plays.
 *
 * TestGesture is a gesture that knows and exposes whether it is Undone,
 * Playing, or has Played. Most gestures have asynchronous transition, that is,
 * they go from [org.geepawhill.contentment.step.TestGesture.State.Playing] to
 * [TestGesture.State.Played] independently. Since it has no way to decide
 * whether it has independently finished running, TestGesture adds the method
 * `Finish` to enable a tester to mark that. See [PhraseTest]
 * for a good sample usage.
 *
 * @author GeePaw
 */
class TestGesture : Gesture {

    var state: State = State.Undone
    private var onFinished: OnFinished? = null
    private val autoFinish: Boolean = false

    enum class State {
        Undone, Playing, Played
    }

    override fun fast(context: Context) {
        when (state) {
            State.Undone -> state = State.Played
            else -> badChange("Fast")
        }

    }

    override fun slow(context: Context, onFinished: OnFinished) {
        this.onFinished = onFinished
        when (state) {
            State.Undone -> {
                state = State.Playing
                if (autoFinish) {
                    finish()
                }
            }
            else -> badChange("Slow")
        }
    }

    fun finish() {
        when (state) {
            State.Playing -> {
                if (onFinished == null) throw RuntimeException("No onFinished handler for Step.")
                onFinished!!.run()
                state = State.Played
            }
            else -> badChange("Finish")
        }
    }

    private fun badChange(change: String) {
        throw RuntimeException("$change called on $state Step.")
    }

}
