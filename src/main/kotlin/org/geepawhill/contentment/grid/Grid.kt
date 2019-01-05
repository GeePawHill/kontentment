package org.geepawhill.contentment.grid

import org.geepawhill.contentment.geometry.Point
import org.geepawhill.contentment.geometry.PointPair
import org.geepawhill.contentment.geometry.ViewPort

class Grid(private val bounds: PointPair) {

    @JvmOverloads
    constructor(x0: Double = 0.0, y0: Double = 0.0, x1: Double = ViewPort.WIDTH, y1: Double = ViewPort.HEIGHT) : this(PointPair(x0, y0, x1, y1))

    fun all(): PointPair {
        return bounds
    }

    fun top(): Horizontal {
        return horizontal(0)
    }

    fun bottom(): Horizontal {
        return horizontal(100)
    }

    fun left(): Vertical {
        return vertical(0)
    }

    fun right(): Vertical {
        return vertical(100)
    }

    fun vertical(percent: Int): Vertical {
        return Vertical(bounds, percent.toDouble())
    }

    fun horizontal(percent: Int): Horizontal {
        return Horizontal(bounds, percent.toDouble())
    }

    fun point(vertical: Vertical, horizontal: Horizontal): Point {
        return Point(vertical.points().from.x, horizontal.points().from.y)
    }

    fun area(fromXPercent: Int, fromYPercent: Int, toXPercent: Int, toYPercent: Int): PointPair {
        return area(vertical(fromXPercent), horizontal(fromYPercent), vertical(toXPercent), horizontal(toYPercent))
    }

    fun area(fromX: Vertical, fromY: Horizontal, toX: Vertical, toY: Horizontal): PointPair {
        return PointPair(
                fromX.points().from.x,
                fromY.points().from.y,
                toX.points().to.x,
                toY.points().to.y)
    }

    fun nested(newLeft: Vertical, newTop: Horizontal, newRight: Vertical, newBottom: Horizontal): Grid {
        return Grid(newLeft.points().from.x, newTop.points().from.y, newRight.points().from.x, newBottom.points().from.y)
    }

    fun nested(fromXPercent: Int, fromYPercent: Int, toXPercent: Int, toYPercent: Int): Grid {
        return nested(vertical(fromXPercent), horizontal(fromYPercent), vertical(toXPercent), horizontal(toYPercent))
    }

    fun point(xPercent: Int, yPercent: Int): Point {
        return point(vertical(xPercent), horizontal(yPercent))
    }
}
