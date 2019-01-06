package org.geepawhill.contentment.timing

class Timing private constructor(private var weightOrMs: Double) {

    val isWeighted: Boolean
        get() = weightOrMs < 0.0

    fun weight(): Double {
        if (!isWeighted) throw RuntimeException("Asked for weight from non-weighted Timing.")
        return -weightOrMs
    }

    fun ms(): Double {
        if (isWeighted) throw RuntimeException("Asked for fixed from weighted Timing.")
        return weightOrMs
    }

    fun fix(ms: Double) {
        if (!isWeighted) throw RuntimeException("Tried to fix Timing twice.")
        weightOrMs = ms
    }

    override fun toString(): String {
        return if (isWeighted)
            String.format("%.0fw", -weightOrMs)
        else
            String.format("%.0fm", weightOrMs)
    }

    companion object {
        private val INSTANT = ms(0.0)

        fun ms(ms: Double): Timing {
            return Timing(ms)
        }

        fun instant(): Timing {
            return INSTANT
        }

        fun weighted(weight: Double): Timing {
            return Timing(-weight)
        }
    }
}
