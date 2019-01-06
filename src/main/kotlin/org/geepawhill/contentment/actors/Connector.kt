package org.geepawhill.contentment.actors

import javafx.scene.Group
import javafx.scene.transform.Rotate
import org.geepawhill.contentment.actor.Actor
import org.geepawhill.contentment.actor.ScriptWorld
import org.geepawhill.contentment.core.GroupSource
import org.geepawhill.contentment.format.Format
import org.geepawhill.contentment.fragments.Entrance
import org.geepawhill.contentment.fragments.Mark
import org.geepawhill.contentment.geometry.Bezier
import org.geepawhill.contentment.geometry.BezierSource
import org.geepawhill.contentment.geometry.Point
import org.geepawhill.contentment.geometry.PointPair
import org.geepawhill.contentment.position.Position
import org.geepawhill.contentment.timing.Timing
import step.Timed
import java.util.*

class Connector(private val world: ScriptWorld, destination: Group) : Actor {
    private val mainStep: Mark
    private val fromTopStep: Mark
    private val fromBottomStep: Mark
    private val toTopStep: Mark
    private val toBottomStep: Mark

    private var points: ArrowPoints? = null

    private var steps: ArrayList<Mark>? = null

    private var chosenMain: Bezier? = null
    private var chosenFromTop: Bezier? = null
    private var chosenFromBottom: Bezier? = null
    private var chosenToTop: Bezier? = null
    private var chosenToBottom: Bezier? = null
    private val entrance: Entrance
    private val group: Group = Group()

    private val connectorPoints: ConnectorPoints

    init {
        this.entrance = Entrance(group)
        this.connectorPoints = ConnectorPoints(world)
        this.mainStep = Mark(group, MainBezierSource())
        this.fromTopStep = Mark(group, FromTopBezierSource())
        this.fromBottomStep = Mark(group, FromBottomBezierSource())
        this.toTopStep = Mark(group, ToTopBezierSource())
        this.toBottomStep = Mark(group, ToBottomBezierSource())
    }

    @JvmOverloads
    fun from(target: Point, withHead: Boolean = false): Connector {
        connectorPoints.from(target, withHead)
        return this
    }

    @JvmOverloads
    fun from(target: String, withHead: Boolean = false): Connector {
        return from(world.actor(target).entrance(), withHead)
    }

    @JvmOverloads
    fun from(target: GroupSource, withHead: Boolean = false): Connector {
        connectorPoints.from(target, withHead)
        return this
    }

    @JvmOverloads
    fun to(target: Point, withHead: Boolean = false): Connector {
        connectorPoints.to(target, withHead)
        return this
    }

    @JvmOverloads
    fun to(target: String, withHead: Boolean = false): Connector {
        return to(world.actor(target).entrance(), withHead)
    }

    @JvmOverloads
    fun to(target: GroupSource, withHead: Boolean = false): Connector {
        connectorPoints.to(target, withHead)
        return this
    }

    override fun format(format: Format) {
        this.mainStep.format(format)
        this.fromTopStep.format(format)
        this.fromBottomStep.format(format)
        this.toTopStep.format(format)
        this.toBottomStep.format(format)
    }

    internal inner class MainBezierSource : BezierSource {

        override fun get(): Bezier {
            if (chosenMain == null) {
                points = compute()
                chosenMain = chooseBezier(points!!.main)
                chosenFromTop = chooseBezier(points!!.fromTop)
                chosenToTop = chooseBezier(points!!.toTop)
                chosenFromBottom = chooseBezier(points!!.fromBottom)
                chosenToBottom = chooseBezier(points!!.toBottom)
            }
            return chosenMain!!
        }
    }

    internal inner class FromTopBezierSource : BezierSource {
        override fun get(): Bezier {
            return chosenFromTop!!
        }
    }

    internal inner class FromBottomBezierSource : BezierSource {

        override fun get(): Bezier {
            return chosenFromBottom!!
        }
    }

    internal inner class ToTopBezierSource : BezierSource {
        override fun get(): Bezier {
            return chosenToTop!!
        }
    }

    internal inner class ToBottomBezierSource : BezierSource {
        override fun get(): Bezier {
            return chosenToBottom!!
        }
    }

    fun chooseBezier(points: PointPair): Bezier {
        val variance = points.distance() * .1
        val chosen = Bezier(
                points.from,
                world.jiggle(points.along(world.nextDouble()), 1.0, variance),
                world.jiggle(points.along(world.nextDouble()), 1.0, variance),
                points.to)
        return chosen
    }

    override fun draw(ms: Double): Connector {
        steps = ArrayList()
        chosenMain = null
        if (connectorPoints.arrowheadAtFrom) {
            steps!!.add(fromTopStep)
            steps!!.add(fromBottomStep)
        }
        if (connectorPoints.arrowheadAtTo) {
            steps!!.add(toTopStep)
            steps!!.add(toBottomStep)
        }
        val sequence = Timed(ms)
        sequence.add(Timing.weighted(.9), mainStep)
        for (step in steps!!) {
            sequence.add(Timing.weighted(.1), step)
        }
        world.add(sequence)
        return this
    }

    fun compute(): ArrowPoints {
        val main = connectorPoints.computeMainLine()
        return makeArrowPoints(main)
    }

    private fun makeArrowPoints(target: PointPair): ArrowPoints {
        val pointStandOffFromTarget = 4.0
        val main = PointPair(target.standoffFrom(pointStandOffFromTarget), target.standoffTo(pointStandOffFromTarget))

        val arrowStandoffFromEnd = 14.0
        val toOffset = main.standoffTo(arrowStandoffFromEnd)
        val toTop = rotateWing(-40.0, main.to, toOffset)
        val toBottom = rotateWing(40.0, main.to, toOffset)

        val fromOffset = main.standoffFrom(arrowStandoffFromEnd)
        val fromTop = rotateWing(-40.0, main.from, fromOffset)
        val fromBottom = rotateWing(40.0, main.from, fromOffset)

        return ArrowPoints(main, toTop, toBottom, fromTop, fromBottom)
    }

    private fun rotateWing(angle: Double, pivot: Point, target: Point): PointPair {
        val rotate = Rotate(angle, pivot.x, pivot.y)
        val top = Point(rotate.transform(target.x, target.y))
        return PointPair(top, pivot)
    }

    override fun at(position: Position) {}

    fun entrance(): Entrance {
        return entrance
    }

    override fun group(): Group {
        return group
    }
}
