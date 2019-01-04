package org.geepawhill.contentment.test;

import static org.geepawhill.contentment.test.ContentmentAssertions.assertThat;

import org.geepawhill.contentment.geometry.*;
import org.junit.jupiter.api.Test;


public class ContentmentAssertionsTest
{
	@Test
	public void points()
	{
		Point one = new Point(100,100);
		Point two = new Point(100,100);
		assertThat(one).isEqualTo(two);
	}
	
	@Test
	public void withinOne()
	{
		Point one = new Point(100,100);
		Point two = new Point(100,100.4);
		assertThat(one).isEqualTo(two);
	}
	
	@Test
	public void pairs()
	{
		PointPair one = new PointPair(0,0,100,100);
		PointPair two = new PointPair(0,0,100,100);
		assertThat(one).isEqualTo(two);
	}
}
