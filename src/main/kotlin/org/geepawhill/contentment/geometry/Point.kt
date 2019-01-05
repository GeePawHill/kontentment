package org.geepawhill.contentment.geometry

import javafx.geometry.Point2D

class Point(val x: Double, val y: Double) {

    constructor(source: Point2D) : this(source.x, source.y) {}

    fun add(xmove: Double, ymove: Double): Point {
        return Point(x + xmove, y + ymove)
    }

    fun add(add: Point): Point {
        return add(add.x, add.y)
    }

    fun xDistance(to: Point): Double {
        return to.x - x
    }

    fun yDistance(to: Point): Double {
        return to.y - y
    }

    fun distance(to: Point): Double {
        val xdistance = xDistance(to)
        val ydistance = yDistance(to)
        return Math.sqrt(xdistance * xdistance + ydistance * ydistance)
    }

    fun along(fraction: Double, to: Point): Point {
        return Point(x + fraction * xDistance(to), y + fraction * yDistance(to))
    }

    override fun hashCode(): Int {
        val prime = 31
        var result = 1
        var temp: Long
        temp = java.lang.Double.doubleToLongBits(x)
        result = prime * result + (temp xor temp.ushr(32)).toInt()
        temp = java.lang.Double.doubleToLongBits(y)
        result = prime * result + (temp xor temp.ushr(32)).toInt()
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (javaClass != other.javaClass) return false
        val other = other as Point?
        if (java.lang.Double.doubleToLongBits(x) != java.lang.Double.doubleToLongBits(other!!.x)) return false
        return if (java.lang.Double.doubleToLongBits(y) != java.lang.Double.doubleToLongBits(other.y)) false else true
    }

    override fun toString(): String {
        return String.format("(%1$.0f, %2$.0f)", x, y)
    }
}
