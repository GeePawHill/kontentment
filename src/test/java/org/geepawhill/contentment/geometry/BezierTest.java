package org.geepawhill.contentment.geometry;

import static org.assertj.core.api.Assertions.within;
import static org.geepawhill.contentment.test.ContentmentAssertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BezierTest
{

	private Point start;
	private Point end;
	private Bezier linear;

	@BeforeEach
	public void before()
	{
		start = new Point(0d, 0d);
		end = new Point(100d, 100d);
		linear = new Bezier(start, end);
	}

	@Test
	public void constructor()
	{
		PointPair line = new PointPair(linear.getStart(), linear.getEnd());
		assertThat(linear.getStart()).isEqualTo(start);
		assertThat(linear.getEnd()).isEqualTo(end);
		assertThat(linear.getHandle1()).isEqualTo(line.along(1d / 3d));
		assertThat(linear.getHandle2()).isEqualTo(line.along(2d / 3d));
	}

	@Test
	public void along()
	{
		assertThat(linear.along(0d)).isEqualTo(start);
		assertThat(linear.along(1d)).isEqualTo(end);
		assertThat(linear.along(.5d)).isGoodEnough(new PointPair(start, end).center(), within(0.1d));
		for (int i = 0; i < 11; i++)
		{
			double fraction = ((double) i) / 10;
			assertThat(linear.along(fraction)).isGoodEnough(new PointPair(0d, 0d, 100d, 100d).along(fraction), within(0.1d));
			assertThat(linear.along(fraction)).isGoodEnough(new PointPair(0d, 0d, 100d, 100d).along(fraction), within(0.1d));
		}
	}
	
	@Test
	public void linearHalf()
	{
		Bezier split = linear.split(.5d);
		assertThat(split.getEnd()).isGoodEnough(new Point(50d, 50d), within(0.1d));
	}

	@Test
	public void linearQuarter()
	{
		Bezier split = linear.split(.25d);
		assertThat(split.getEnd()).isGoodEnough(new Point(25d, 25d), within(0.1d));
	}


}
