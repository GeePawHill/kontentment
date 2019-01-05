package org.geepawhill.contentment.core;

import org.geepawhill.contentment.rhythm.Rhythm;

import javafx.scene.Group;

public class Context
{
	public final Group canvas;

	private Rhythm rhythm;

	public Context()
	{
		this.canvas = new Group();
	}
	
	public Rhythm rhythm()
	{
		return rhythm;
	}
	
	public void setRhythm(Rhythm rhythm)
	{
		this.rhythm = rhythm;
	}
	
	public long beat()
	{
		return rhythm.beat();
	}

	public void wipe()
	{
		canvas.getChildren().clear();
	}
	
}
