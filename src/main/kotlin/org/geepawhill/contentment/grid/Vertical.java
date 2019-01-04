package org.geepawhill.contentment.grid;

import org.geepawhill.contentment.geometry.PointPair;

public class Vertical
{
	
	private PointPair points;

	public Vertical(PointPair bounds, double percent)
	{
		double x = bounds.along(percent/100d).x;
		points = new PointPair( x,bounds.from.y,x,bounds.to.y);
	}
	
	public PointPair points()
	{
		return points;
	}
	
}
