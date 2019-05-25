package org.geepawhill.contentment.fragments

import javafx.scene.Group
import org.geepawhill.contentment.core.Context
import org.geepawhill.contentment.core.Fragment

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
        val after = from + fraction * (to - from)
        target.opacity = after
        return true
    }
}
