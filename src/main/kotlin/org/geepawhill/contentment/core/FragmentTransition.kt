package org.geepawhill.contentment.core

import javafx.animation.Transition
import javafx.util.Duration
/**
 * Create a FragmentTransition that lasts ms long (minimum 1), playing the
 * Fragment against the Context, then calling the OnFinished callback.
 *
 * @param ms -- desired milliseconds
 * @param fragment -- fragment to interpolate across the time
 * @param context -- context in which to do the fragment
 * @param onFinished -- callback for when it's done
 */
class FragmentTransition(ms: Long, private val fragment: Fragment, private val context: Context?, private val onFinished: OnFinished) : Transition() {
    private val ms: Long

    init {
        if (context == null) {
            println("null context")
        }
        this.ms = if (ms > 0) ms else 1
    }

    /**
     * Override the animation's play to also set the two key parameters from the
     * constructor and guarantee that the prepare method is called.
     *
     * @see javafx.animation.Animation.play
     */
    override fun play() {
        cycleDuration = Duration.millis(ms.toDouble())
        setOnFinished { event -> onFinished.run() }
        fragment.prepare(context!!)
        super.play()
    }

    /**
     * Overrides the base classes interpolate to force the fragment to draw,
     * possibly ending the animation. <br></br>
     *
     * @see javafx.animation.Transition.interpolate
     */
    override fun interpolate(fraction: Double) {
        if (!fragment.interpolate(context!!, fraction)) finishEarly()
    }

    private fun finishEarly() {
        // Note: OnFinished is called automatically by super when there's no call to #stop.
        stop()
        onFinished.run()
    }
}
