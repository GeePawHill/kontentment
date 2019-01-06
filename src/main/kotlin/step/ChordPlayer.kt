package step

import org.geepawhill.contentment.core.Context
import org.geepawhill.contentment.core.Gesture
import org.geepawhill.contentment.core.OnFinished

import java.util.ArrayList

internal class ChordPlayer : SlowPlayer {
    private var onFinished: OnFinished? = null
    private var finished: Int = 0

    /* (non-Javadoc)
     * @see org.geepawhill.contentment.step.SlowPlayer#play(org.geepawhill.contentment.core.Context, org.geepawhill.contentment.core.OnFinished, java.util.ArrayList)
     */
    override fun play(context: Context, onFinished: OnFinished, gestures: ArrayList<Gesture>) {
        this.onFinished = onFinished
        this.finished = gestures.size
        for (Step in gestures) {
            Step.slow(context, object : OnFinished {
                override fun run() {
                    next()
                }
            })
        }
    }

    private operator fun next() {
        finished -= 1
        if (finished == 0) onFinished!!.run()
    }

}