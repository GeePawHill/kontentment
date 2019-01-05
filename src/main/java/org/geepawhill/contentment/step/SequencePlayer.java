package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.*;

class SequencePlayer implements SlowPlayer
{
	private int current;
	private OnFinished onFinished;
	private ArrayList<Gesture> playables;
	private Context context;

	@Override
	public void play(Context context, OnFinished onFinished, ArrayList<Gesture> playables)
		{
		this.context = context;
		this.onFinished = onFinished;
		this.playables = playables;
		this.current = 0;
		if (playables.isEmpty())
		{
			onFinished.run();
		}
		else
		{
			Gesture step = playables.get(current);
			step.slow(context, () -> next());
		}
	}

	private void next()
	{
		current += 1;
		if (current == playables.size())
		{
			onFinished.run();
		}
		else
		{
			Gesture step = playables.get(current);
			step.slow(context, () -> next());
		}
	}
	
}