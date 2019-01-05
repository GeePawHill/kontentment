package org.geepawhill.contentment.step;

import java.util.ArrayList;

import org.geepawhill.contentment.core.*;

public class Phrase implements Gesture
{
	
	private SlowPlayer player;
	protected final ArrayList<Gesture> gestures;
	
	public static Phrase phrase()
	{
		return new Phrase(new SequencePlayer());
	}
	
	public static Phrase chord()
	{
		return new Phrase(new ChordPlayer());
	}
	
	public Phrase(SlowPlayer player)
	{
		this.player = player;
		this.gestures = new ArrayList<>();
	}

	public Phrase add(Gesture Step)
	{
		gestures.add(Step);
		return this;
	}
	
	@Override
	public void fast(Context context)
	{
		for(Gesture step : gestures)
		{
			step.fast(context);
		}
	}
	
	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		player.play(context,onFinished,gestures);
	}	
}
