package org.geepawhill.contentment.geometry

import javafx.scene.transform.Rotate

class ArrowHead(origin: Point, target: Point, size: Double, gap: Double) {
    val top: PointPair
    val bottom: PointPair

    init {
        val arrowPoint = target.approach(origin, gap)
        val wing = PointPair(arrowPoint, arrowPoint.approach(origin, size))
        top = PointPair(target, rotateWing(-40.0, wing.from, wing.to))
        bottom = PointPair(target, rotateWing(40.0, wing.from, wing.to))
    }

    private fun rotateWing(angle: Double, pivot: Point, target: Point): Point {
        return Point(Rotate(angle, pivot.x, pivot.y).transform(target.x, target.y))
    }
}