package org.geepawhill.contentment.actors

import javafx.scene.Group
import javafx.scene.paint.Color
import org.geepawhill.contentment.actor.Actor
import org.geepawhill.contentment.actor.ScriptWorld
import org.geepawhill.contentment.core.GroupSource
import org.geepawhill.contentment.format.Format
import org.geepawhill.contentment.fragments.Entrance
import org.geepawhill.contentment.fragments.Mark
import org.geepawhill.contentment.geometry.Bezier
import org.geepawhill.contentment.geometry.Point
import org.geepawhill.contentment.geometry.PointPair
import org.geepawhill.contentment.position.Position
import org.geepawhill.contentment.step.Timed
import org.geepawhill.contentment.style.Frames
import org.geepawhill.contentment.timing.Timing

class Cross(private val world: ScriptWorld, private val target: GroupSource, private val xsize: Double, private val ysize: Double, private val offset: Point) : Actor {
    private val group = Group()
    private val crossFormat = Format(Frames.frame(Color.RED, 7.0, .7))
    private val entrance = Entrance(group)
    private val leftToRight = Mark(group) { leftToRight() }
    private val rightToLeft = Mark(group) { rightToLeft() }

    constructor(world: ScriptWorld, target: GroupSource, xsize: Double, ysize: Double, xoffset: Double, yoffset: Double) : this(world, target, xsize, ysize, Point(xoffset, yoffset)) {}

    override fun draw(ms: Double): Cross {
        leftToRight.format(crossFormat)
        rightToLeft.format(crossFormat)
        val timed = Timed(ms)
        timed.add(Timing.weighted(.5), leftToRight)
        timed.add(Timing.weighted(.5), rightToLeft)
        world.add(timed)
        return this
    }

    private fun leftToRight(): Bezier {
        val xAdditive = xsize / 2.0
        val yAdditive = ysize / 2.0
        val center = PointPair(target.group()).center().add(offset)
        return Bezier(Point(center.x - xAdditive, center.y - yAdditive), Point(center.x + xAdditive, center.y + yAdditive))
    }

    private fun rightToLeft(): Bezier {
        val xadditive = xsize / 2.0
        val yadditive = ysize / 2.0
        val center = PointPair(target.group()).center().add(offset)
        return Bezier(
                Point(center.x + xadditive, center.y - yadditive), Point(center.x - xadditive, center.y + yadditive))
    }

    override fun format(format: Format) {}

    override fun at(position: Position) {}

    fun entrance(): Entrance {
        return entrance
    }

    override fun group(): Group {
        return group
    }
}
