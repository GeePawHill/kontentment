package org.geepawhill.contentment.grid;

import org.geepawhill.contentment.geometry.PointPair;

public class Horizontal
{
	PointPair points;

	public Horizontal(PointPair bounds, double percent)
	{
		double y = bounds.along(percent/100d).y;
		points = new PointPair( bounds.from.x,y,bounds.to.x,y);
	}

	public PointPair points()
	{
		return points;
	}

}
