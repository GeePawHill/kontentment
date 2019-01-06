package step

import org.geepawhill.contentment.core.Context
import org.geepawhill.contentment.core.Gesture
import org.geepawhill.contentment.core.OnFinished
import java.util.*

interface SlowPlayer {

    fun play(context: Context, onFinished: OnFinished, gestures: ArrayList<Gesture>)

}