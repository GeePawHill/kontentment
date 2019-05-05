package org.geepawhill.contentment.test

import org.assertj.core.api.AbstractAssert
import org.geepawhill.contentment.geometry.PointPair
import org.geepawhill.contentment.test.ContentmentAssertions.Companion.assertThat

class PointPairAssert(actual: PointPair) : AbstractAssert<PointPairAssert, PointPair>(actual, PointPairAssert::class.java) {

    override fun isEqualTo(expected: Any): PointPairAssert {
        if (expected !is PointPair) failWithMessage("Not a PointPair")
        val expectedPair = expected as PointPair
        assertThat(expectedPair.from).isEqualTo(actual.from)
        assertThat(expectedPair.to).isEqualTo(actual.to)
        return this
    }
}
