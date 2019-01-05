package org.geepawhill.contentment.grid

import org.geepawhill.contentment.geometry.PointPair

class Vertical(bounds: PointPair, percent: Double) {

    private val points: PointPair

    init {
        val x = bounds.along(percent / 100.0).x
        points = PointPair(x, bounds.from.y, x, bounds.to.y)
    }

    fun points(): PointPair {
        return points
    }

}
