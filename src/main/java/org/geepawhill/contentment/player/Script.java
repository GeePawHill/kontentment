package org.geepawhill.contentment.player;

import java.util.ArrayList;

import org.geepawhill.contentment.rhythm.*;

import javafx.scene.media.MediaPlayer;

public class Script
{
	ArrayList<Keyframe> steps;
	private Rhythm rhythm;

	public Script()
	{
		this(new SimpleRhythm());
	}
	
	public Script(Rhythm rhythm)
	{
		this.rhythm = rhythm;
		this.steps = new ArrayList<>();
	}

	public int size()
	{
		return steps.size();
	}

	public Keyframe get(int index)
	{
		return steps.get(index);
	}

	public Script add(Keyframe step)
	{
		steps.add(step);
		return this;
	}

	public Rhythm rhythm()
	{
		return rhythm;
	}

	public MediaPlayer getMediaPlayer()
	{
		return rhythm.getMediaPlayer();
	}
}