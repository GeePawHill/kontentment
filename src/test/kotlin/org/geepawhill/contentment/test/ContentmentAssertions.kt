package org.geepawhill.contentment.test

import org.assertj.core.api.Assertions
import org.geepawhill.contentment.geometry.Point
import org.geepawhill.contentment.geometry.PointPair

class ContentmentAssertions {
    companion object : Assertions() {
        fun assertThat(actual: Point): PointAssert {
            return PointAssert(actual)
        }

        fun assertThat(actual: PointPair): PointPairAssert {
            return PointPairAssert(actual)
        }
    }

}
