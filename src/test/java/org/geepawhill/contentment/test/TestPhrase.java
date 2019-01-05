package org.geepawhill.contentment.test;

import org.geepawhill.contentment.core.*;
import org.geepawhill.contentment.step.Phrase;

public class TestPhrase extends Phrase
{
	
	public boolean isBefore;
	public boolean isPlaying;
	private OnFinished onFinished;
	
	
	public TestPhrase()
	{
		super(null);
		isBefore=true;
		isPlaying=false;
	}

	@Override
	public void fast(Context context)
	{
		isBefore=false;
		isPlaying=false;
	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		this.onFinished = onFinished;
		isBefore=false;
		isPlaying=true;
	}
	
	public void finishPlaying()
	{
		isPlaying=false;
		isBefore=false;
		onFinished.run();
	}

}
