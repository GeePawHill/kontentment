package org.geepawhill.contentment.fragments;

import org.geepawhill.contentment.core.*;

import javafx.scene.Group;

/**
 * Changes the opacity of a given Group target.
 * 
 * @author GeePaw
 *
 */
public class Fader implements Fragment
{
	private Group target;
	private double from;
	private double to;

	public Fader(Group target, double to)
	{
		this.target = target;
		this.to = to;
	}

	@Override
	public void prepare(Context context)
	{
		this.from = target.getOpacity();
	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		target.setOpacity(from + fraction * (to - from));
		return true;
	}
}
