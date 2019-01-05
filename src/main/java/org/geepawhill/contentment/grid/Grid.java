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
		this(0,0, ViewPort.Companion.getWIDTH(), ViewPort.Companion.getHEIGHT());
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
		return new Point(vertical.points().getFrom().getX(), horizontal.points.getFrom().getY());
	}
	
	public PointPair area(int fromXPercent, int fromYPercent, int toXPercent, int toYPercent)
	{
		return area(vertical(fromXPercent),horizontal(fromYPercent),vertical(toXPercent),horizontal(toYPercent));
	}
	
	public PointPair area(Vertical fromX,Horizontal fromY,Vertical toX,Horizontal toY)
	{
		return new PointPair(
                fromX.points().getFrom().getX(),
                fromY.points().getFrom().getY(),
                toX.points().getTo().getX(),
                toY.points().getTo().getY());
	}

	public Grid nested(Vertical newLeft, Horizontal newTop, Vertical newRight, Horizontal newBottom)
	{
		return new Grid(newLeft.points().getFrom().getX(), newTop.points().getFrom().getY(), newRight.points().getFrom().getX(), newBottom.points().getFrom().getY());
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
