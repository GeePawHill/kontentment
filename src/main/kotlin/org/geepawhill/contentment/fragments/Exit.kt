package org.geepawhill.contentment.fragments

import org.geepawhill.contentment.core.*

import javafx.scene.Group
import java.lang.NullPointerException

/**
 * Removes a group from the canvas without otherwise altering it. No-ops if
 * the group wasn't on the canvas. Throws if the group was null.
 *
 * @author GeePaw
 */
class Exit(private val target: Group) : Fragment {

    override fun prepare(context: Context) {}

    override fun interpolate(context: Context, fraction: Double): Boolean {
        val parent = target.parent as Group? ?: throw NullPointerException("Exit when group has no parent.")
        parent.children.remove(target)
        return false
    }

}
