package org.geepawhill.contentment.geometry;

import java.util.Random;

public class Jiggler
{
	public double range;
	public double probability;
	private Random random;

	public Jiggler(double probability, double range)
	{
		this.random = new Random();
		this.range = range;
		this.probability = probability;
	}

	public Jiggler()
	{
		this(0d, 0d);
	}
	
	public Point jiggle(Point point)
	{
		if(probability==0d) return point;
		double newX = point.x;
		double newY = point.y;
		if (random.nextDouble() < probability)
		{
			double sign = random.nextDouble() > .5 ? -1 : +1;
			double change = random.nextDouble() * range;
			newX += sign * change;
		}
		if (random.nextDouble() < probability)
		{
			double sign = random.nextDouble() > .5 ? -1 : +1;
			double change = random.nextDouble() * range;
			newY += sign * change;
		}
		return new Point(newX, newY);
	}
}
