package org.geepawhill.contentment.player;

import org.geepawhill.contentment.core.Gesture;

public class Keyframe
{
	public final long target;
	public final Gesture phrase;
	
	public Keyframe(Gesture phrase)
	{
		this(0,phrase);
	}

	public Keyframe(long target, Gesture phrase)
	{
		this.target = target*1000;
		this.phrase = phrase;
	}
}
