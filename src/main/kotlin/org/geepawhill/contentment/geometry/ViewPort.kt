package org.geepawhill.contentment.geometry

interface ViewPort {
    companion object {
        val WIDTH = 1600.0
        val HEIGHT = 900.0

        val CENTERX = WIDTH / 2.0
        val CENTERY = HEIGHT / 2.0

        val CENTER = Point(CENTERX, CENTERY)
    }
}
