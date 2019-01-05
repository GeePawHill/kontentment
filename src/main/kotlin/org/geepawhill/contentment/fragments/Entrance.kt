package org.geepawhill.contentment.fragments

import org.geepawhill.contentment.core.*

import javafx.scene.Group

/**
 * Makes a group and adds it to the canvas, making it available via get for later
 * manipulators.
 *
 * @author GeePaw
 */
class Entrance(private val group: Group) : Fragment, GroupSource {

    override fun prepare(context: Context) {
        group.opacity = 1.0
        context.canvas.children.add(group)
    }

    override fun interpolate(context: Context, fraction: Double): Boolean {
        return false
    }

    override fun group(): Group {
        return group
    }
}
