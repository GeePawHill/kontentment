package org.geepawhill.contentment.test;

import org.assertj.core.api.Assertions;
import org.geepawhill.contentment.geometry.*;

public class ContentmentAssertions extends Assertions 
{
	public static PointAssert assertThat(Point actual)
	{
		return new PointAssert(actual);
	}
	
	public static PointPairAssert assertThat(PointPair actual)
	{
		return new PointPairAssert(actual);
	}

}
