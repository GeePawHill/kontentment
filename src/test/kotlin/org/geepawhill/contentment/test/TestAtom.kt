package org.geepawhill.contentment.test

import java.util.ArrayList

import org.geepawhill.contentment.core.*

class TestAtom : Fragment {
    var fractions: ArrayList<Double>

    init {
        fractions = ArrayList()
    }

    override fun prepare(context: Context) {
        fractions.add(0.0)
    }

    override fun interpolate(context: Context, fraction: Double): Boolean {
        fractions.add(fraction)
        return true
    }
}