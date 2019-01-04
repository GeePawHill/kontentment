package org.geepawhill.contentment.grid;

import org.geepawhill.contentment.geometry.*;

public class Grid
{
	private final PointPair bounds;
	
	public Grid(double x0,double y0, double x1, double y1)
	{
		this(new PointPair(x0,y0,x1,y1));
	}

	public Grid(PointPair bounds)
	{
		this.bounds = bounds;
	}
	
	public Grid()
	{
		this(0,0,ViewPort.WIDTH,ViewPort.HEIGHT);
	}

	public PointPair all()
	{
		return bounds;
	}
	
	public Horizontal top()
	{
		return horizontal(0);
	}
	
	public Horizontal bottom()
	{
		return horizontal(100);
	}

	public Vertical left()
	{
		return vertical(0);
	}

	public Vertical right()
	{
		return vertical(100);
	}

	public Vertical vertical(int percent)
	{
		return new Vertical(bounds,percent);
	}

	public Horizontal horizontal(int percent)
	{
		return new Horizontal(bounds,percent);
	}

	public Point point(Vertical vertical, Horizontal horizontal)
	{
		return new Point(vertical.points().from.x,horizontal.points.from.y);
	}
	
	public PointPair area(int fromXPercent, int fromYPercent, int toXPercent, int toYPercent)
	{
		return area(vertical(fromXPercent),horizontal(fromYPercent),vertical(toXPercent),horizontal(toYPercent));
	}
	
	public PointPair area(Vertical fromX,Horizontal fromY,Vertical toX,Horizontal toY)
	{
		return new PointPair(
				fromX.points().from.x,
				fromY.points().from.y,
				toX.points().to.x,
				toY.points().to.y);
	}

	public Grid nested(Vertical newLeft, Horizontal newTop, Vertical newRight, Horizontal newBottom)
	{
		return new Grid(newLeft.points().from.x,newTop.points().from.y,newRight.points().from.x,newBottom.points().from.y);
	}

	public Grid nested(int fromXPercent, int fromYPercent, int toXPercent, int toYPercent)
	{
		return nested(vertical(fromXPercent),horizontal(fromYPercent),vertical(toXPercent),horizontal(toYPercent));
	}

	public Point point(int xPercent, int yPercent)
	{
		return point(vertical(xPercent),horizontal(yPercent));
	}
}
