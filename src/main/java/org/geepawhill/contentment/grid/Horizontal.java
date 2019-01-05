package org.geepawhill.contentment.grid;

import org.geepawhill.contentment.geometry.PointPair;

public class Horizontal
{
	PointPair points;

	public Horizontal(PointPair bounds, double percent)
	{
		double y = bounds.along(percent / 100d).getY();
		points = new PointPair(bounds.getFrom().getX(),y, bounds.getTo().getX(),y);
	}

	public PointPair points()
	{
		return points;
	}

}
