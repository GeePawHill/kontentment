package org.geepawhill.contentment.grid

import org.geepawhill.contentment.geometry.PointPair

class Horizontal(bounds: PointPair, percent: Double) {
    private var points: PointPair

    init {
        val y = bounds.along(percent / 100.0).y
        points = PointPair(bounds.from.x, y, bounds.to.x, y)
    }

    fun points(): PointPair {
        return points
    }

}
