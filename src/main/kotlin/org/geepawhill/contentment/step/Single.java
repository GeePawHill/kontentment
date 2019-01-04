package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.*;
import org.geepawhill.contentment.timing.Timing;

public class Single implements Gesture
{
	private Fragment fragment;
	private Timing timing;

	public Single(Timing timing,Fragment fragment)
	{
		this.timing = timing;
		this.fragment = fragment;
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		new FragmentTransition((long)timing.ms(), fragment, context, onFinished).play();
	}

	@Override
	public void fast(Context context)
	{
		fragment.prepare(context);
		fragment.interpolate(context, 1d);
	}
	
	@Override
	public String toString()
	{
		return fragment.toString();
	}
}
