package org.geepawhill.contentment.style;

import java.util.*;

public class Dash
{
	public final List<Double> array;

	private Dash(Double... array)
	{
		this.array = Arrays.asList(array);
	}

	public static Dash solid()
	{
		return new Dash(new Double[0]);
	}

	public static Dash dash(Double... dash)
	{
		return new Dash(dash);
	}

}
