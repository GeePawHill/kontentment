package org.geepawhill.contentment.fragments

import org.geepawhill.contentment.core.*

import javafx.scene.Group

/**
 * Changes the opacity of a given Group target.
 *
 * @author GeePaw
 */
class Fader(private val target: Group, private val to: Double) : Fragment {
    private var from: Double = 0.toDouble()

    override fun prepare(context: Context) {
        this.from = target.opacity
    }

    override fun interpolate(context: Context, fraction: Double): Boolean {
        target.opacity = from + fraction * (to - from)
        return true
    }
}
