package org.geepawhill.contentment.timing

class Scheduler {
    private var accumulatedAbsolute: Double = 0.toDouble()
    private var accumulatedRelative: Double = 0.toDouble()

    fun schedule(total: Double, vararg timings: Timing): Double {
        gatherTotals(*timings)
        if (total == 0.0) return noTotalSupplied()
        val distribute = amountLeftToDistribute(total)
        return distributeRelatives(distribute, *timings)
    }

    private fun distributeRelatives(distribute: Double, vararg timings: Timing): Double {
        var afterDistribution = accumulatedAbsolute
        for (timing in timings) {
            if (timing.isWeighted) {
                val ratio = timing.weight()
                if (ratio != 0.0) {
                    var ms = ratio * distribute / accumulatedRelative
                    if (ms < .1) ms = .1
                    afterDistribution += ms
                    timing.fix(ms)
                }
            }
        }
        return afterDistribution
    }

    private fun amountLeftToDistribute(total: Double): Double {
        val distribute = total - accumulatedAbsolute
        //		if (distribute < 0d) throw new RuntimeException(ABSOLUTE_OVERRUN+" Total: "+total+" Allocated: "+accumulatedAbsolute);
        return distribute
    }

    private fun noTotalSupplied(): Double {
        if (accumulatedRelative > 0.0) {
            throw RuntimeException(RELATIVES_BUT_NO_TOTAL)
        }
        return accumulatedAbsolute
    }

    private fun gatherTotals(vararg timings: Timing) {
        accumulatedAbsolute = 0.0
        accumulatedRelative = 0.0
        for (timing in timings) {
            if (!timing.isWeighted) {
                accumulatedAbsolute += timing.ms()
            } else {
                accumulatedRelative += timing.weight()
            }
        }
    }

    companion object {

        val RELATIVES_BUT_NO_TOTAL = "Included relative timing with no absolute total."
        val ABSOLUTE_OVERRUN = "Absolutes are bigger than allocated."
    }
}
