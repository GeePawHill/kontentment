package test

import org.assertj.core.api.AbstractAssert
import org.assertj.core.data.Offset
import org.assertj.core.util.DoubleComparator
import org.geepawhill.contentment.geometry.Point

class PointAssert(actual: Point) : AbstractAssert<PointAssert, Point>(actual, PointAssert::class.java) {

    override fun isEqualTo(expected: Any): PointAssert {
        if (expected !is Point) failWithMessage("Not a point.")
        val expectedPoint = expected as Point
        val offset = 1.0
        val comparator = DoubleComparator(offset)
        if (comparator.compare(actual.x, expectedPoint.x) != 0)
            failWithMessage("X Not close enough. Expected: " + expectedPoint.x + " within " + offset + " but was: " + actual.x)
        if (comparator.compare(actual.y, expectedPoint.y) != 0)
            failWithMessage("y Not close enough. Expected: " + expectedPoint.y + " within " + offset + " but was: " + actual.y)
        return this
    }

    fun isGoodEnough(expected: Point, offset: Offset<Double>): PointAssert {
        val comparator = DoubleComparator(offset.value)
        if (comparator.compare(actual.x, expected.x) != 0)
            failWithMessage("X Not close enough. Expected: " + expected.x + " within " + offset + " but was: " + actual.x)
        if (comparator.compare(actual.y, expected.y) != 0)
            failWithMessage("y Not close enough. Expected: " + expected.y + " within " + offset + " but was: " + actual.y)
        return this
    }
}
