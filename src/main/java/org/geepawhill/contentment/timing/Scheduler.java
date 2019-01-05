package org.geepawhill.contentment.timing;

public class Scheduler
{

	public static final String RELATIVES_BUT_NO_TOTAL = "Included relative timing with no absolute total.";
	public static final String ABSOLUTE_OVERRUN = "Absolutes are bigger than allocated.";
	private double accumulatedAbsolute;
	private double accumulatedRelative;

	public double schedule(double total, Timing... timings)
	{
		gatherTotals(timings);
		if (total == 0d) return noTotalSupplied();
		double distribute = amountLeftToDistribute(total);
		return distributeRelatives(distribute, timings);
	}

	private double distributeRelatives(double distribute, Timing... timings)
	{
		double afterDistribution = accumulatedAbsolute;
		for (Timing timing : timings)
		{
			if (timing.isWeighted())
			{
				double ratio = timing.weight();
				if (ratio != 0d)
				{
					double ms = (ratio * distribute) / accumulatedRelative;
					if (ms < .1d) ms = .1d;
					afterDistribution += ms;
					timing.fix(ms);
				}
			}
		}
		return afterDistribution;
	}

	private double amountLeftToDistribute(double total)
	{
		double distribute = total - accumulatedAbsolute;
//		if (distribute < 0d) throw new RuntimeException(ABSOLUTE_OVERRUN+" Total: "+total+" Allocated: "+accumulatedAbsolute);
		return distribute;
	}

	private double noTotalSupplied()
	{
		if (accumulatedRelative > 0d) { throw new RuntimeException(RELATIVES_BUT_NO_TOTAL); }
		return accumulatedAbsolute;
	}

	private void gatherTotals(Timing... timings)
	{
		accumulatedAbsolute = 0d;
		accumulatedRelative = 0d;
		for (Timing timing : timings)
		{
			if (!timing.isWeighted())
			{
				accumulatedAbsolute += timing.ms();
			}
			else
			{
				accumulatedRelative += timing.weight();
			}
		}
	}
}
