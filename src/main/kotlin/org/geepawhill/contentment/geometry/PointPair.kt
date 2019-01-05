package org.geepawhill.contentment.geometry

import javafx.geometry.Bounds
import javafx.scene.Node

class PointPair(val from: Point, val to: Point) {

    constructor(fromX: Double, fromY: Double, toX: Double, toY: Double) : this(Point(fromX, fromY), Point(toX, toY)) {}

    constructor(bounds: Bounds) : this(Point(bounds.minX, bounds.minY), Point(bounds.maxX, bounds.maxY)) {}

    constructor(node: Node) : this(node.boundsInParent) {}

    fun along(fraction: Double): Point {
        return from.along(fraction, to)
    }

    fun distance(): Double {
        return from.distance(to)
    }

    fun center(): Point {
        return Point(centerX(), centerY())
    }

    fun centerY(): Double {
        return (from.y + to.y) / 2.0
    }

    fun centerX(): Double {
        return (from.x + to.x) / 2.0
    }

    fun north(): Point {
        return Point(centerX(), from.y)
    }

    fun south(): Point {
        return Point(centerX(), to.y)
    }

    fun east(): Point {
        return Point(to.x, centerY())
    }

    fun west(): Point {
        return Point(from.x, centerY())
    }

    fun northeast(): Point {
        return Point(to.x, from.y)
    }

    fun northwest(): Point {
        return Point(from.x, from.y)
    }

    fun southeast(): Point {
        return Point(to.x, to.y)
    }

    fun southwest(): Point {
        return Point(from.x, to.y)
    }

    fun intersects(other: PointPair): Point? {
        var result: Point? = null

        val s1_x = to.x - from.x
        val s1_y = to.y - from.y
        val s2_x = other.to.x - other.from.x
        val s2_y = other.to.y - other.from.y
        val s = (-s1_y * (from.x - other.from.x) + s1_x * (from.y - other.from.y)) / (-s2_x * s1_y + s1_x * s2_y)
        val t = (s2_x * (from.y - other.from.y) - s2_y * (from.x - other.from.x)) / (-s2_x * s1_y + s1_x * s2_y)

        if (s >= 0 && s <= 1 && t >= 0 && t <= 1) {
            result = Point(from.x + t * s1_x, from.y + t * s1_y)
        }

        return result
    }

    fun quadIntersects(other: PointPair): Point? {
        val northLine = PointPair(from.x, from.y, to.x, from.y)
        val northIntersect = northLine.intersects(other)
        if (northIntersect != null) return northIntersect
        val southLine = PointPair(from.x, to.y, to.x, to.y)
        val southIntersect = southLine.intersects(other)
        if (southIntersect != null) return southIntersect
        val eastLine = PointPair(to.x, from.y, to.x, to.y)
        val eastIntersect = eastLine.intersects(other)
        if (eastIntersect != null) return eastIntersect
        val westLine = PointPair(from.x, from.y, from.x, to.y)
        val westIntersect = westLine.intersects(other)
        return westIntersect
    }

    fun grow(delta: Double): PointPair {
        return grow(delta, delta)
    }

    fun grow(deltaX: Double, deltaY: Double): PointPair {
        return PointPair(from.x - deltaX, from.y - deltaY, to.x + deltaX, to.y + deltaY)
    }

    fun change(deltaLeft: Double, deltaTop: Double, deltaRight: Double, deltaBottom: Double): PointPair {
        return PointPair(from.x - deltaLeft, from.y - deltaTop, to.x + deltaRight, to.y + deltaBottom)
    }

    fun width(): Double {
        return to.x - from.x
    }

    fun height(): Double {
        return to.y - from.y
    }

    fun northLine(): PointPair {
        return PointPair(from.x, from.y, to.x, from.y)
    }

    fun southLine(): PointPair {
        return PointPair(from.x, to.y, to.x, to.y)
    }

    fun eastLine(): PointPair {
        return PointPair(to.x, from.y, to.x, to.y)
    }

    fun westLine(): PointPair {
        return PointPair(from.x, from.y, from.x, to.y)
    }

    fun canonical(): PointPair {
        val newFromX = if (from.x > to.x) to.x else from.x
        val newToX = if (from.x > to.x) from.x else to.x
        val newFromY = if (from.y > to.y) to.y else from.y
        val newToY = if (from.y > to.y) from.y else to.y
        return PointPair(newFromX, newFromY, newToX, newToY)
    }

    override fun toString(): String {
        return from.toString() + " " + to.toString()
    }

    fun xCenterLine(): PointPair {
        return PointPair(north(), south())
    }

    fun yCenterLine(): PointPair {
        return PointPair(west(), east())
    }

    fun standoffTo(amount: Double): Point {
        return along(1.0 - amount / distance())
    }

    fun standoffFrom(amount: Double): Point {
        return along(amount / distance())
    }

    companion object {

        fun centerAt(center: Point, width: Double, height: Double): PointPair {
            val from = Point(center.x - width / 2.0, center.y - height / 2.0)
            val to = Point(center.x + width / 2.0, center.y + height / 2.0)
            return PointPair(from, to)
        }
    }

}
