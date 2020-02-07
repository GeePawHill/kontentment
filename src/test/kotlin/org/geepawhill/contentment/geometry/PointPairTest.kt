package org.geepawhill.contentment.geometry

import javafx.geometry.BoundingBox
import javafx.scene.shape.Rectangle
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class PointPairTest {

    @Test
    fun constructors() {
        val pairs = arrayOf(PointPair(50.0, 150.0, 200.0, 400.0), PointPair(Point(50.0, 150.0), Point(200.0, 400.0)), PointPair(BoundingBox(50.0, 150.0, 150.0, 250.0)), PointPair(Rectangle(50.0, 150.0, 150.0, 250.0)))
        for (pair in pairs) {
            assertEquals(50.0, pair.from.x, 0.0001)
            assertEquals(150.0, pair.from.y, 0.0001)
            assertEquals(200.0, pair.to.x, 0.0001)
            assertEquals(400.0, pair.to.y, 0.0001)
        }
    }

    @Test
    fun centers() {
        val pair = PointPair(50.0, 150.0, 200.0, 400.0)
        assertEquals(125.0, pair.centerX(), 0.0001)
        assertEquals(275.0, pair.centerY(), 0.0001)
        assertEquals(Point(125.0, 275.0), pair.center())
    }

    @Test
    fun directions() {
        val pair = PointPair(50.0, 150.0, 200.0, 400.0)
        assertEquals(Point(50.0, 150.0), pair.northwest())
        assertEquals(Point(200.0, 150.0), pair.northeast())
        assertEquals(Point(125.0, 150.0), pair.north())
        assertEquals(Point(50.0, 400.0), pair.southwest())
        assertEquals(Point(200.0, 400.0), pair.southeast())
        assertEquals(Point(125.0, 400.0), pair.south())
        assertEquals(Point(50.0, 275.0), pair.west())
        assertEquals(Point(200.0, 275.0), pair.east())
    }

    @Test
    fun intersects() {
        val base = PointPair(100.0, 100.0, 200.0, 200.0)
        val should = PointPair(100.0, 200.0, 200.0, 100.0)
        assertEquals(Point(150.0, 150.0), base.intersects(should))
    }

    @Test
    fun noIntersects() {
        val base = PointPair(100.0, 100.0, 200.0, 200.0)
        val should = PointPair(300.0, 100.0, 300.0, 200.0)
        assertNull(base.intersects(should))
    }

    @Test
    fun boxIntersects() {
        val base = PointPair(100.0, 100.0, 200.0, 200.0)
        val farSouthCenter = Point(base.centerX(), 300.0)
        val center = base.center()
        val should = base.boxIntersects(PointPair(farSouthCenter, center))
        assertEquals(base.south(), should)
    }
}
