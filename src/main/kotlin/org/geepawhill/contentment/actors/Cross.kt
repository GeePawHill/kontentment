package org.geepawhill.contentment.actors

import org.geepawhill.contentment.actor.*
import org.geepawhill.contentment.core.GroupSource
import org.geepawhill.contentment.format.Format
import org.geepawhill.contentment.fragments.*
import org.geepawhill.contentment.geometry.*
import org.geepawhill.contentment.position.Position
import step.Timed
import org.geepawhill.contentment.style.Frames
import org.geepawhill.contentment.timing.Timing

import javafx.scene.Group
import javafx.scene.paint.Color

class Cross(private val world: ScriptWorld, destination: Group, private val target: GroupSource, private val xsize: Double, private val ysize: Double, private val offset: Point) : Actor {
    private val leftToRight: Mark
    private val rightToLeft: Mark
    private val crossFormat: Format
    private val entrance: Entrance
    private val group: Group = Group()

    constructor(world: ScriptWorld, destination: Group, target: Appearance<out Actor>, size: Double) : this(world, destination, target.entrance(), size, size, Point(0.0, 0.0)) {}

    constructor(world: ScriptWorld, destination: Group, target: GroupSource, xsize: Double, ysize: Double, xoffset: Double, yoffset: Double) : this(world, destination, target, xsize, ysize, Point(xoffset, yoffset)) {}

    init {
        this.entrance = Entrance(group)
        crossFormat = Format(Frames.frame(Color.RED, 7.0, .7))
        leftToRight = Mark(group, LeftToRightBezierSource())
        rightToLeft = Mark(group, RightToLeftBezierSource())
    }

    override fun draw(ms: Double): Cross {
        leftToRight.format(crossFormat)
        rightToLeft.format(crossFormat)
        val timed = Timed(ms)
        timed.add(Timing.weighted(.5), leftToRight)
        timed.add(Timing.weighted(.5), rightToLeft)
        world.add(timed)
        return this
    }

    internal inner class LeftToRightBezierSource : BezierSource {

        override fun get(): Bezier {
            val xadditive = xsize / 2.0
            val yadditive = ysize / 2.0
            val center = PointPair(target.group()).center().add(offset)
            return Bezier(Point(center.x - xadditive, center.y - yadditive), Point(center.x + xadditive, center.y + yadditive))
        }
    }

    internal inner class RightToLeftBezierSource : BezierSource {

        override fun get(): Bezier {
            val xadditive = xsize / 2.0
            val yadditive = ysize / 2.0
            val center = PointPair(target.group()).center().add(offset)
            return Bezier(
                    Point(center.x + xadditive, center.y - yadditive), Point(center.x - xadditive, center.y + yadditive))
        }
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
