package org.geepawhill.contentment.geometry

import org.assertj.core.data.Offset
import org.geepawhill.contentment.test.ContentmentAssertions.Companion.assertThat
import org.junit.jupiter.api.Test

class ArrowHeadTest {
    val zero = Point(0, 0)

    @Test
    fun `from directly left`() {
        val head = ArrowHead(Point(-50, 0), zero, 20.0, 0.0)
        assertThat(head.top.to).isGoodEnough(Point(-15, 12), Offset.offset(1.0))
        assertThat(head.bottom.to).isGoodEnough(Point(-15, -12), Offset.offset(1.0))
    }

    @Test
    fun `from directly above`() {
        val head = ArrowHead(Point(0, -50), zero, 20.0, 0.0)
        assertThat(head.top.to).isGoodEnough(Point(-12, -15), Offset.offset(1.0))
        assertThat(head.bottom.to).isGoodEnough(Point(12, -15), Offset.offset(1.0))
    }
}