package org.geepawhill.contentment.timing;

public class Timing
{
	static private final Timing INSTANT = ms(0d);

	private double weightOrMs;

	private Timing(double weightOrMs)
	{
		this.weightOrMs = weightOrMs;
	}

	static public Timing ms(double ms)
	{
		return new Timing(ms);
	}

	static public Timing instant()
	{
		return INSTANT;
	}

	static public Timing weighted(double weight)
	{
		return new Timing(-weight);
	}

	public boolean isWeighted()
	{
		return weightOrMs < 0d;
	}

	public double weight()
	{
		if (!isWeighted()) throw new RuntimeException("Asked for weight from non-weighted Timing.");
		return -weightOrMs;
	}

	public double ms()
	{
		if (isWeighted()) throw new RuntimeException("Asked for fixed from weighted Timing.");
		return weightOrMs;
	}

	public void fix(double ms)
	{
		if (!isWeighted()) throw new RuntimeException("Tried to fix Timing twice.");
		weightOrMs = ms;
	}
	
	public String toString()
	{
		if(isWeighted()) return String.format("%.0fw",-weightOrMs);
		else return String.format("%.0fm", weightOrMs);
	}
}
