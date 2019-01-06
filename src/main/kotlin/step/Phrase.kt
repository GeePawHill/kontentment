package step

import java.util.ArrayList

import org.geepawhill.contentment.core.*

open class Phrase(private val player: SlowPlayer) : Gesture {
    protected val gestures: ArrayList<Gesture>

    init {
        this.gestures = ArrayList()
    }

    fun add(Step: Gesture): Phrase {
        gestures.add(Step)
        return this
    }

    override fun fast(context: Context) {
        for (step in gestures) {
            step.fast(context)
        }
    }

    override fun slow(context: Context, onFinished: OnFinished) {
        player.play(context, onFinished, gestures)
    }

    companion object {

        fun phrase(): Phrase {
            return Phrase(SequencePlayer())
        }

        fun chord(): Phrase {
            return Phrase(ChordPlayer())
        }
    }
}