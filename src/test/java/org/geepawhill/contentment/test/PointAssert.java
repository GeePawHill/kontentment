package org.geepawhill.contentment.test;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.data.Offset;
import org.assertj.core.util.DoubleComparator;
import org.geepawhill.contentment.geometry.Point;

public class PointAssert extends AbstractAssert<PointAssert, Point>
{
	public PointAssert(Point actual)
	{
		super(actual, PointAssert.class);
	}

	public PointAssert isEqualTo(Object expected)
	{
		if(!(expected instanceof Point)) failWithMessage("Not a point.");
		Point expectedPoint = (Point) expected;
		double offset = 1d;
		DoubleComparator comparator = new DoubleComparator(offset);
		if (comparator.compare(actual.getX(), expectedPoint.getX()) != 0)
			failWithMessage("X Not close enough. Expected: " + expectedPoint.getX() + " within " + offset + " but was: " + actual.getX());
		if (comparator.compare(actual.getY(), expectedPoint.getY()) != 0)
			failWithMessage("y Not close enough. Expected: " + expectedPoint.getY() + " within " + offset + " but was: " + actual.getY());
		return this;
	}

	public PointAssert isGoodEnough(Point expected, Offset<Double> offset)
	{
		DoubleComparator comparator = new DoubleComparator(offset.value);
		if (comparator.compare(actual.getX(), expected.getX()) != 0)
			failWithMessage("X Not close enough. Expected: " + expected.getX() + " within " + offset + " but was: " + actual.getX());
		if (comparator.compare(actual.getY(), expected.getY()) != 0)
			failWithMessage("y Not close enough. Expected: " + expected.getY() + " within " + offset + " but was: " + actual.getY());
		return this;
	}
}
