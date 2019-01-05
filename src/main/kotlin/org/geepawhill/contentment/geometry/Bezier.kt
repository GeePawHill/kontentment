package org.geepawhill.contentment.geometry

import javafx.scene.shape.*

class Bezier {
    val start: Point
    val handle1: Point
    val handle2: Point
    val end: Point

    constructor(points: PointPair) : this(points.from, points.to) {}

    constructor(start: Point, end: Point) {
        val pair = PointPair(start, end)
        this.handle1 = pair.along(1.0 / 3.0)
        this.handle2 = pair.along(2.0 / 3.0)
        this.start = start
        this.end = end
    }

    constructor(start: Point, handle1: Point, handle2: Point, end: Point) {
        this.start = start
        this.handle1 = handle1
        this.handle2 = handle2
        this.end = end
    }

    fun along(fraction: Double): Point {
        // A = -K0 + 3K1 + -3K2 + K3
        // B = 3K0 + -6K1 + 3K2
        // C = -3K0 + 3K1
        // D = K0

        val ax = -start.x + 3.0 * handle1.x - 3.0 * handle2.x + end.x
        val ay = -start.y + 3.0 * handle1.y - 3.0 * handle2.y + end.y
        val bx = 3.0 * start.x - 6.0 * handle1.x + 3.0 * handle2.x
        val by = 3.0 * start.y - 6.0 * handle1.y + 3.0 * handle2.y
        val cx = -3.0 * start.x + 3.0 * handle1.x
        val cy = -3.0 * start.y + 3.0 * handle1.y
        val dx = start.x
        val dy = start.y

        val x = ax * (fraction * fraction * fraction) + bx * (fraction * fraction) + cx * fraction + dx
        val y = ay * (fraction * fraction * fraction) + by * (fraction * fraction) + cy * fraction + dy
        return Point(x, y)
    }

    fun splitToPath(fraction: Double, path: Path) {
        path.elements.clear()
        if (fraction == 0.0) return
        val before = split(fraction)
        path.elements.add(MoveTo(before.start.x, before.start.y))
        path.elements.add(CubicCurveTo(before.handle1.x, before.handle1.y, before.handle2.x, before.handle2.y,
                before.end.x, before.end.y))
    }

    fun split(fraction: Double): Bezier {
        // this is de casteljau's algorithm, see: https://stackoverflow.com/questions/2613788/algorithm-for-inserting-points-in-a-piecewise-cubic-b%C3%A9zier-path
        // P0_1 = (1-t)*P0 + t*P1
        // P1_2 = (1-t)*P1 + t*P2
        // P2_3 = (1-t)*P2 + t*P3
        val P0_1 = proportionalInterpolation(fraction, start, handle1)
        val P1_2 = proportionalInterpolation(fraction, handle1, handle2)
        val P2_3 = proportionalInterpolation(fraction, handle2, end)

        // P01_12 = (1-t)*P0_1 + t*P1_2
        val P01_12 = proportionalInterpolation(fraction, P0_1, P1_2)
        // P12_23 = (1-t)*P1_2 + t*P2_3
        val P12_23 = proportionalInterpolation(fraction, P1_2, P2_3)

        // P0112_1223 = (1-t)*P01_12 + t*P12_23
        val P0112_1223 = proportionalInterpolation(fraction, P01_12, P12_23)

        val before = Bezier(start, P0_1, P01_12, P0112_1223)
        return before
    }

    private fun proportionalInterpolation(fraction: Double, first: Point, second: Point): Point {
        val oneMinusT = 1.0 - fraction
        return Point(oneMinusT * first.x + fraction * second.x, oneMinusT * first.y + fraction * second.y)
    }
}
