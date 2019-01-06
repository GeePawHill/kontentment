package step

import org.geepawhill.contentment.core.*
import org.geepawhill.contentment.timing.Timing

class Single(private val timing: Timing, private val fragment: Fragment) : Gesture {

    override fun slow(context: Context, onFinished: OnFinished) {
        FragmentTransition(timing.ms().toLong(), fragment, context, onFinished).play()
    }

    override fun fast(context: Context) {
        fragment.prepare(context)
        fragment.interpolate(context, 1.0)
    }

    override fun toString(): String {
        return fragment.toString()
    }
}
