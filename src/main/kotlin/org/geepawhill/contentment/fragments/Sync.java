package org.geepawhill.contentment.fragments;

import org.geepawhill.contentment.core.*;
/**
 * Wait for the context's rhythm to hit a particular mark in seconds
 * 
 * @author GeePaw
 *
 */
public class Sync implements Fragment
{

	private long mark;

	public Sync(long mark)
	{
		this.mark = mark;
	}

	@Override
	public void prepare(Context context)
	{
	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		return context.beat()<mark;
	}

}
