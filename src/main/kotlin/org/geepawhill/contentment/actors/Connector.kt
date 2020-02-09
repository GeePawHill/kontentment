package org.geepawhill.contentment.actors

import javafx.scene.Group
import javafx.scene.transform.Rotate
import org.geepawhill.contentment.actor.Actor
import org.geepawhill.contentment.actor.ScriptWorld
import org.geepawhill.contentment.core.GroupSource
import org.geepawhill.contentment.format.Format
import org.geepawhill.contentment.fragments.Entrance
import org.geepawhill.contentment.fragments.Mark
import org.geepawhill.contentment.geometry.ArrowHead
import org.geepawhill.contentment.geometry.Bezier
import org.geepawhill.contentment.geometry.Point
import org.geepawhill.contentment.geometry.PointPair
import org.geepawhill.contentment.position.Position
import org.geepawhill.contentment.step.Timed
import org.geepawhill.contentment.timing.Timing
import java.lang.Double.max
import java.lang.Double.min
import java.util.*

class Connector(private val world: ScriptWorld) : Actor {
    private val mainStep: Mark
    private val fromTopStep: Mark
    private val fromBottomStep: Mark
    private val toTopStep: Mark
    private val toBottomStep: Mark

    private var steps: ArrayList<Mark>? = null

    private var chosenMain: Bezier? = null
    private var chosenFromTop: Bezier? = null
    private var chosenFromBottom: Bezier? = null
    private var chosenToTop: Bezier? = null
    private var chosenToBottom: Bezier? = null
    private val entrance: Entrance
    private val group: Group = Group()

    private val connectorEnds: ConnectorEnds

    init {
        this.entrance = Entrance(group)
        this.connectorEnds = ConnectorEnds(world)
        this.mainStep = Mark(group) { layout(); chosenMain!! }
        this.fromTopStep = Mark(group) { layout(); chosenFromTop!! }
        this.fromBottomStep = Mark(group) { layout(); chosenFromBottom!! }
        this.toTopStep = Mark(group) { layout(); chosenToTop!! }
        this.toBottomStep = Mark(group) { layout(); chosenToBottom!! }
    }

    private fun layout() {
        if (chosenMain != null) return
        val idealLine = connectorEnds.idealLine()
        val pointStandOffFromTarget = 4.0
        val main = PointPair(idealLine.standoffFrom(pointStandOffFromTarget), idealLine.standoffTo(pointStandOffFromTarget))

        val fromArrowHead = ArrowHead(main.to, main.from, 14.0, 0.0)
        val toArrowHead = ArrowHead(main.from, main.to, 14.0, 0.0)

        chosenMain = chooseBezier(main)
        chosenFromTop = chooseBezier(fromArrowHead.top)
        chosenToTop = chooseBezier(toArrowHead.top)
        chosenFromBottom = chooseBezier(fromArrowHead.bottom)
        chosenToBottom = chooseBezier(toArrowHead.bottom)
    }

    @JvmOverloads
    fun from(target: Point, withHead: Boolean = false): Connector {
        connectorEnds.from(target, withHead)
        return this
    }

    @JvmOverloads
    fun from(x: Int, y: Int, withHead: Boolean = false): Connector {
        connectorEnds.from(Point(x.toDouble(), y.toDouble()), withHead)
        return this
    }

    @JvmOverloads
    fun from(target: String, withHead: Boolean = false): Connector {
        return from(world.actor(target).entrance(), withHead)
    }

    @JvmOverloads
    fun from(target: GroupSource, withHead: Boolean = false): Connector {
        connectorEnds.from(target, withHead)
        return this
    }

    @JvmOverloads
    fun to(target: Point, withHead: Boolean = false): Connector {
        connectorEnds.to(target, withHead)
        return this
    }

    @JvmOverloads
    fun to(x: Int, y: Int, withHead: Boolean = false): Connector {
        connectorEnds.to(Point(x.toDouble(), y.toDouble()), withHead)
        return this
    }

    @JvmOverloads
    fun to(target: String, withHead: Boolean = false): Connector {
        return to(world.actor(target).entrance(), withHead)
    }

    @JvmOverloads
    fun to(target: GroupSource, withHead: Boolean = false): Connector {
        connectorEnds.to(target, withHead)
        return this
    }

    override fun format(format: Format) {
        this.mainStep.format(format)
        this.fromTopStep.format(format)
        this.fromBottomStep.format(format)
        this.toTopStep.format(format)
        this.toBottomStep.format(format)
    }

    fun chooseBezier(points: PointPair): Bezier {
        val variance = points.distance() * .1
        val chosen = Bezier(
                points.from,
                world.jiggle(points.along(max(world.nextDouble(), .25)), 1.0, variance),
                world.jiggle(points.along(min(world.nextDouble(), .75)), 1.0, variance),
                points.to)
        return chosen
    }

    override fun draw(ms: Double): Connector {
        steps = ArrayList()
        chosenMain = null
        if (connectorEnds.arrowheadAtFrom) {
            steps!!.add(fromTopStep)
            steps!!.add(fromBottomStep)
        }
        if (connectorEnds.arrowheadAtTo) {
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
