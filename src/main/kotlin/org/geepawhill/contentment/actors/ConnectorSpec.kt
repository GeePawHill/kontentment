package org.geepawhill.contentment.actors

import org.geepawhill.contentment.core.GroupSource
import org.geepawhill.contentment.geometry.Point
import org.geepawhill.contentment.geometry.PointPair

class ConnectorSpec {

    private var fromGroup: GroupSource? = null
    private var fromPoint: Point? = null
    var arrowheadAtFrom: Boolean = false

    private var toGroup: GroupSource? = null
    private var toPoint: Point? = null
    var arrowheadAtTo: Boolean = false

    var arcHeight: Double = 0.0

    init {
        this.fromGroup = GroupSource.NONE
        this.fromPoint = Point(0.0, 0.0)
        this.toGroup = GroupSource.NONE
        this.toPoint = Point(0.0, 0.0)
    }

    fun idealLine(): PointPair {
        val startLine = guessStartLine()
        val main = PointPair(adjustFromIfGroup(startLine)!!, adjustToIfGroup(startLine)!!)
        return main
    }

    fun arc(height: Int) {
        arcHeight = height.toDouble()
    }

    fun from(target: Point, withHead: Boolean) {
        fromGroup = GroupSource.NONE
        fromPoint = target
        arrowheadAtFrom = withHead
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
        return toGrown.boxIntersects(startLine)
    }

    private fun adjustFromIfGroup(startLine: PointPair): Point? {
        if (fromGroup === GroupSource.NONE) return startLine.from
        val fromGrown = PointPair(fromGroup!!.group())
        return fromGrown.boxIntersects(startLine)
    }

    private fun guessStartLine(): PointPair {
        val fromCenter = if (fromGroup === GroupSource.NONE) fromPoint else PointPair(fromGroup!!.group()).center()
        val toCenter = if (toGroup === GroupSource.NONE) toPoint else PointPair(toGroup!!.group()).center()
        val startLine = PointPair(fromCenter!!, toCenter!!)
        return startLine
    }
}