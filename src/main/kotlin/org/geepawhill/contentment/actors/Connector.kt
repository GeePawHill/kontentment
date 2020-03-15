package org.geepawhill.contentment.actors

import javafx.scene.Group
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

    private val spec: ConnectorSpec
    private var beziers: ConnectorBeziers? = null

    private val entrance: Entrance
    private val group: Group = Group()


    init {
        this.entrance = Entrance(group)
        this.spec = ConnectorSpec()
        this.mainStep = Mark(group) { layout(); beziers!!.chosenMain }
        this.fromTopStep = Mark(group) { layout(); beziers!!.chosenFromTop }
        this.fromBottomStep = Mark(group) { layout(); beziers!!.chosenFromBottom }
        this.toTopStep = Mark(group) { layout(); beziers!!.chosenToTop }
        this.toBottomStep = Mark(group) { layout(); beziers!!.chosenToBottom }
    }

    private fun layout() {
        if (beziers != null) return
        beziers = if (spec.arcHeight == 0.0) layoutStraight()
        else layoutArc()
    }

    private fun layoutArc(): ConnectorBeziers {
        val idealLine = spec.idealLine()
        val pointStandOffFromTarget = 4.0
        val straight = PointPair(idealLine.standoffFrom(pointStandOffFromTarget), idealLine.standoffTo(pointStandOffFromTarget))
        val height = spec.arcHeight
        val xUnitVector = (straight.from.x - straight.to.x) / straight.distance()
        val yUnitVector = (straight.from.y - straight.to.y) / straight.distance()
        val firstq = straight.along(.25)
        val c1 = Point(firstq.x + (height * yUnitVector), firstq.y + (height * xUnitVector))

        val thirdq = straight.along(.75)
        val c2 = Point(thirdq.x + (height * yUnitVector), thirdq.y + (height * xUnitVector))

        val secondq = straight.along(.5)
        val midpoint = Point(secondq.x + (2 * height * yUnitVector), secondq.y + (2 * height * xUnitVector))
        val main = Bezier(straight.from, c1, c2, straight.to)
        val fromArrowHead = ArrowHead(midpoint, main.start, 14.0, 0.0)
        val toArrowHead = ArrowHead(midpoint, main.end, 14.0, 0.0)
        return ConnectorBeziers(
                main,
                chooseBezier(fromArrowHead.top),
                chooseBezier(fromArrowHead.bottom),
                chooseBezier(toArrowHead.top),
                chooseBezier(toArrowHead.bottom)
        )
    }

    private fun layoutStraight(): ConnectorBeziers {
        val idealLine = spec.idealLine()
        val pointStandOffFromTarget = 4.0
        val main = PointPair(idealLine.standoffFrom(pointStandOffFromTarget), idealLine.standoffTo(pointStandOffFromTarget))

        val fromArrowHead = ArrowHead(main.to, main.from, 14.0, 0.0)
        val toArrowHead = ArrowHead(main.from, main.to, 14.0, 0.0)

        return ConnectorBeziers(
                chooseBezier(main),
                chooseBezier(fromArrowHead.top),
                chooseBezier(fromArrowHead.bottom),
                chooseBezier(toArrowHead.top),
                chooseBezier(toArrowHead.bottom)
        )
    }

    @JvmOverloads
    fun from(target: Point, withHead: Boolean = false): Connector {
        spec.from(target, withHead)
        return this
    }

    @JvmOverloads
    fun from(x: Int, y: Int, withHead: Boolean = false): Connector {
        spec.from(Point(x.toDouble(), y.toDouble()), withHead)
        return this
    }

    @JvmOverloads
    fun from(target: String, withHead: Boolean = false): Connector {
        return from(world.actor(target).entrance(), withHead)
    }

    @JvmOverloads
    fun from(target: GroupSource, withHead: Boolean = false): Connector {
        spec.from(target, withHead)
        return this
    }

    @JvmOverloads
    fun to(target: Point, withHead: Boolean = false): Connector {
        spec.to(target, withHead)
        return this
    }

    @JvmOverloads
    fun to(x: Int, y: Int, withHead: Boolean = false): Connector {
        spec.to(Point(x.toDouble(), y.toDouble()), withHead)
        return this
    }

    @JvmOverloads
    fun to(target: String, withHead: Boolean = false): Connector {
        return to(world.actor(target).entrance(), withHead)
    }

    @JvmOverloads
    fun to(target: GroupSource, withHead: Boolean = false): Connector {
        spec.to(target, withHead)
        return this
    }

    fun arc(height: Int): Connector {
        spec.arc(height)
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
        val steps = ArrayList<Mark>()
        if (spec.arrowheadAtFrom) {
            steps.add(fromTopStep)
            steps.add(fromBottomStep)
        }
        if (spec.arrowheadAtTo) {
            steps.add(toTopStep)
            steps.add(toBottomStep)
        }
        val sequence = Timed(ms)
        sequence.add(Timing.weighted(.9), mainStep)
        for (step in steps) {
            sequence.add(Timing.weighted(.1), step)
        }
        world.add(sequence)
        return this
    }

    override fun at(position: Position) {}

    fun entrance(): Entrance {
        return entrance
    }

    override fun group(): Group {
        return group
    }
}
