package org.geepawhill.contentment.test;

import static org.geepawhill.contentment.test.ContentmentAssertions.assertThat;

import org.assertj.core.api.AbstractAssert;
import org.geepawhill.contentment.geometry.PointPair;

public class PointPairAssert extends AbstractAssert<PointPairAssert, PointPair>
{
	public PointPairAssert(PointPair actual)
	{
		super(actual, PointPairAssert.class);
	}

	public PointPairAssert isEqualTo(Object expected)
	{
		if(!(expected instanceof PointPair)) failWithMessage("Not a PointPair");
		PointPair expectedPair = (PointPair) expected;
		assertThat(expectedPair.getFrom()).isEqualTo(actual.getFrom());
		assertThat(expectedPair.getTo()).isEqualTo(actual.getTo());
		return this;
	}
}
