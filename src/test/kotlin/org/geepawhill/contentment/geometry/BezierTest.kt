package org.geepawhill.contentment.geometry

import org.assertj.core.api.Assertions.within
import org.geepawhill.contentment.test.ContentmentAssertions.Companion.assertThat
import org.junit.jupiter.api.Test

class BezierTest {

    private val start = Point(0.0, 0.0)
    private val end = Point(100.0, 100.0)
    private val linear = Bezier(start, end)

    @Test
    fun constructor() {
        val line = PointPair(linear.start, linear.end)
        assertThat(linear.start).isEqualTo(start)
        assertThat(linear.end).isEqualTo(end)
        assertThat(linear.handle1).isEqualTo(line.along(1.0 / 3.0))
        assertThat(linear.handle2).isEqualTo(line.along(2.0 / 3.0))
    }

    @Test
    fun along() {
        assertThat(linear.along(0.0)).isEqualTo(start)
        assertThat(linear.along(1.0)).isEqualTo(end)
        assertThat(linear.along(.5)).isGoodEnough(PointPair(start, end).center(), within(0.1))
        for (i in 0..10) {
            val fraction = i.toDouble() / 10
            assertThat(linear.along(fraction)).isGoodEnough(PointPair(0.0, 0.0, 100.0, 100.0).along(fraction), within(0.1))
            assertThat(linear.along(fraction)).isGoodEnough(PointPair(0.0, 0.0, 100.0, 100.0).along(fraction), within(0.1))
        }
    }

    @Test
    fun linearHalf() {
        val split = linear.split(.5)
        assertThat(split.end).isGoodEnough(Point(50.0, 50.0), within(0.1))
    }

    @Test
    fun linearQuarter() {
        val split = linear.split(.25)
        assertThat(split.end).isGoodEnough(Point(25.0, 25.0), within(0.1))
    }


}
