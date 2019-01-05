package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.*;

class ChordPlayer implements SlowPlayer
{
	private OnFinished onFinished;
	private int finished;
	
	/* (non-Javadoc)
	 * @see org.geepawhill.contentment.step.SlowPlayer#play(org.geepawhill.contentment.core.Context, org.geepawhill.contentment.core.OnFinished, java.util.ArrayList)
	 */
	@Override
	public void play(Context context, OnFinished onFinished, ArrayList<Gesture> gestures)
	{
		this.onFinished = onFinished;
		this.finished = gestures.size();
		for(Gesture Step : gestures)
		{
			Step.slow(context, ()->next());
		}
	}
	
	private void next()
	{
		finished-=1;
		if(finished==0) onFinished.run();
	}
	
}