package org.geepawhill.contentment.geometry

import java.util.Random

class Jiggler(var probability: Double, var range: Double) {
    private val random: Random

    init {
        this.random = Random()
    }

    fun jiggle(point: Point): Point {
        if (probability == 0.0) return point
        var newX = point.x
        var newY = point.y
        if (random.nextDouble() < probability) {
            val sign = (if (random.nextDouble() > .5) -1 else +1).toDouble()
            val change = random.nextDouble() * range
            newX += sign * change
        }
        if (random.nextDouble() < probability) {
            val sign = (if (random.nextDouble() > .5) -1 else +1).toDouble()
            val change = random.nextDouble() * range
            newY += sign * change
        }
        return Point(newX, newY)
    }
}
