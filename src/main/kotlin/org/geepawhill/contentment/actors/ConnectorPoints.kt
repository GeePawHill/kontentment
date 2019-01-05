package org.geepawhill.contentment.actors

import org.geepawhill.contentment.actor.ScriptWorld
import org.geepawhill.contentment.core.GroupSource
import org.geepawhill.contentment.geometry.*

class ConnectorPoints(private val world: ScriptWorld) {

    private var fromGroup: GroupSource? = null
    private var fromPoint: Point? = null
    var arrowheadAtFrom: Boolean = false
    private var toGroup: GroupSource? = null
    private var toPoint: Point? = null
    var arrowheadAtTo: Boolean = false

    init {
        this.fromGroup = GroupSource.NONE
        this.fromPoint = Point(0.0, 0.0)
        this.toGroup = GroupSource.NONE
        this.toPoint = Point(0.0, 0.0)
    }

    fun computeMainLine(): PointPair {
        val startLine = guessStartLine()
        val main = PointPair(adjustFromIfGroup(startLine)!!, adjustToIfGroup(startLine)!!)
        return main
    }

    fun from(target: Point, withHead: Boolean) {
        fromGroup = GroupSource.NONE
        fromPoint = target
        arrowheadAtFrom = withHead
    }

    fun from(actorName: String, withHead: Boolean) {
        from(world.actor(actorName).entrance(), withHead)
    }

    fun from(from: GroupSource, withHead: Boolean) {
        this.fromGroup = from
        arrowheadAtFrom = withHead
    }

    fun to(target: Point, withHead: Boolean) {
        this.toGroup = GroupSource.NONE
        this.toPoint = target
        arrowheadAtTo = withHead
    }

    fun to(to: GroupSource, withHead: Boolean) {
        this.toGroup = to
        arrowheadAtTo = withHead
    }

    private fun adjustToIfGroup(startLine: PointPair): Point? {
        if (toGroup === GroupSource.NONE) return startLine.to
        val toGrown = PointPair(toGroup!!.group())
        return toGrown.quadIntersects(startLine)
    }

    private fun adjustFromIfGroup(startLine: PointPair): Point? {
        if (fromGroup === GroupSource.NONE) return startLine.from
        val fromGrown = PointPair(fromGroup!!.group())
        return fromGrown.quadIntersects(startLine)
    }

    private fun guessStartLine(): PointPair {
        val fromCenter = if (fromGroup === GroupSource.NONE) fromPoint else PointPair(fromGroup!!.group()).center()
        val toCenter = if (toGroup === GroupSource.NONE) toPoint else PointPair(toGroup!!.group()).center()
        val startLine = PointPair(fromCenter!!, toCenter!!)
        return startLine
    }

}