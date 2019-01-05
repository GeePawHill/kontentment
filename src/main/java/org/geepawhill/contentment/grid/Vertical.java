package org.geepawhill.contentment.grid;

import org.geepawhill.contentment.geometry.PointPair;

public class Vertical
{
	
	private PointPair points;

	public Vertical(PointPair bounds, double percent)
	{
		double x = bounds.along(percent / 100d).getX();
		points = new PointPair( x, bounds.getFrom().getY(),x, bounds.getTo().getY());
	}
	
	public PointPair points()
	{
		return points;
	}
	
}
