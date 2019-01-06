package step

import org.geepawhill.contentment.core.Context
import org.geepawhill.contentment.core.Gesture
import org.geepawhill.contentment.core.OnFinished
import java.util.*

internal class SequencePlayer : SlowPlayer {
    private var current: Int = 0
    private var onFinished: OnFinished? = null
    private var playables: ArrayList<Gesture>? = null
    private var context: Context? = null

    override fun play(context: Context, onFinished: OnFinished, gestures: ArrayList<Gesture>) {
        this.context = context
        this.onFinished = onFinished
        this.playables = gestures
        this.current = 0
        if (gestures.isEmpty()) {
            onFinished.run()
        } else {
            val step = gestures[current]
            step.slow(context, object : OnFinished {
                override fun run() {
                    next()
                }
            })
        }
    }

    private operator fun next() {
        current += 1
        if (current == playables!!.size) {
            onFinished!!.run()
        } else {
            val step = playables!![current]
            step.slow(context!!, object : OnFinished {
                override fun run() {
                    next()
                }
            })
        }
    }
}