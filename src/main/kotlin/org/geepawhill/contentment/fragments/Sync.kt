package org.geepawhill.contentment.fragments

import org.geepawhill.contentment.core.*

/**
 * Wait for the context's rhythm to hit a particular mark in seconds
 *
 * @author GeePaw
 */
class Sync(private val mark: Long) : Fragment {

    override fun prepare(context: Context) {}

    override fun interpolate(context: Context, fraction: Double): Boolean {
        return context.beat() < mark
    }

}
