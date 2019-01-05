package org.geepawhill.contentment.actor

import org.geepawhill.contentment.core.GroupSource
import org.geepawhill.contentment.format.Format
import org.geepawhill.contentment.fragments.Entrance
import org.geepawhill.contentment.position.Position

interface Actor : GroupSource {
    fun format(format: Format)
    fun at(position: Position)
    fun draw(ms: Double): Actor
}