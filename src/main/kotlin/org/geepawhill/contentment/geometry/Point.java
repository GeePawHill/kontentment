package org.geepawhill.contentment.geometry;

import javafx.geometry.Point2D;

public class Point
{
	public final double x;
	public final double y;

	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Point(Point2D source)
	{
		this(source.getX(),source.getY());
	}

	public Point add(double xmove, double ymove)
	{
		return new Point(x + xmove, y + ymove);
	}

	public Point add(Point add)
	{
		return add(add.x, add.y);
	}

	public double xDistance(Point to)
	{
		return to.x - x;
	}

	public double yDistance(Point to)
	{
		return to.y - y;
	}

	public double distance(Point to)
	{
		double xdistance = xDistance(to);
		double ydistance = yDistance(to);
		return Math.sqrt(xdistance * xdistance + ydistance * ydistance);
	}

	public Point along(double fraction, Point to)
	{
		return new Point(x + fraction * xDistance(to), y + fraction * yDistance(to));
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Point other = (Point) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) return false;
		return true;
	}

	@Override
	public String toString()
	{
		return String.format("(%1$.0f, %2$.0f)", x, y);
	}
}
