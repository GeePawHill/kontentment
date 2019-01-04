package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.*;
import org.geepawhill.contentment.timing.*;
import org.geepawhill.contentment.utility.Names;

public class Timed implements Gesture
{
	private final Scheduler scheduler;
	private final ArrayList<Fragment> atoms;
	private final ArrayList<Timing> timings;

	private double ms;
	private String name;
	private int current;
	private Context context;
	private OnFinished onFinished;

	public Timed(double ms)
	{
		this.name = Names.make(Timed.class);
		this.atoms = new ArrayList<>();
		this.timings = new ArrayList<>();
		this.ms = ms;
		this.scheduler = new Scheduler();
	}

	public Timed add(Fragment atom)
	{
		return add(Timing.instant(), atom);
	}

	public Timed add(long ms, Fragment atom)
	{
		add(Timing.ms(ms),atom);
		return this;
	}
	
	public Timed add(Timing timing, Fragment atom)
	{
		atoms.add(atom);
		timings.add(timing);
		return this;
	}

	@Override
	public void fast(Context context)
	{
		for (Fragment atom : atoms)
		{
			atom.prepare(context);
			atom.interpolate(context, 1d);
		}
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		this.context = context;
		this.onFinished = onFinished;
		scheduler.schedule(ms, timings.toArray(new Timing[] {}));
		if (!atoms.isEmpty())
		{
			current = 0;
			runCurrent();
		}
		else onFinished.run();
	}

	private void runCurrent()
	{
		Fragment atom = atoms.get(current);
		Timing timing = timings.get(current);
		new FragmentTransition((long)timing.ms(),atom,context,()->next()).play();
	}
	
	private void next()
	{
		current+=1;
		if(current==atoms.size()) onFinished.run();
		else runCurrent();
	}

	@Override
	public String toString()
	{
		return name;
	}

}
