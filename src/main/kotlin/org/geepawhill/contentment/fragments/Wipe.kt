package org.geepawhill.contentment.fragments

import org.geepawhill.contentment.core.*

/**
 * Clears the entire canvas.
 *
 * @author GeePaw
 */
class Wipe : Fragment {

    override fun prepare(context: Context) {
        context.canvas.children.clear()
    }

    override fun interpolate(context: Context, fraction: Double): Boolean {
        return false
    }
}
