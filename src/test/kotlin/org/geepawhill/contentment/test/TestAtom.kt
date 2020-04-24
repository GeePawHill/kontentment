package org.geepawhill.contentment.test

import org.geepawhill.contentment.core.Context
import org.geepawhill.contentment.core.Fragment
import java.util.*

class TestAtom : Fragment {
    var fractions: ArrayList<Double>

    init {
        fractions = ArrayList()
    }

    override fun prepare(context: Context) {
        fractions.add(0.0)
    }

    override fun interpolate(context: Context, fraction: Double): Boolean {
        println("+")
        fractions.add(fraction)
        return true
    }
}