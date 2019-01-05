package org.geepawhill.contentment.test;

import java.util.ArrayList;

import org.geepawhill.contentment.core.*;

public class TestAtom implements Fragment {
	public ArrayList<Double> fractions;
	public TestAtom()
	{
		fractions = new ArrayList<>();
	}

	@Override
	public void prepare(Context context)
	{
		fractions.add(0d);
	}
	
	@Override
	public boolean interpolate(Context context, double fraction)
	{
		fractions.add(fraction);
		return true;
	}
}