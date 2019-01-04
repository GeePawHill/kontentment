package org.geepawhill.contentment.fragments;

import org.geepawhill.contentment.core.*;

import javafx.scene.Group;

/**
 * Makes a group and adds it to the destination, making it available via get for later
 * manipulators.
 * 
 * @author GeePaw
 *
 */
public class Entrance implements Fragment, GroupSource
{
	private Group group;
	private Group destination;

	public Entrance(Group destination, Group group)
	{
		this.destination = destination;
		this.group = group;
	}

	@Override
	public void prepare(Context context)
	{
		group.setOpacity(1);
		if(destination!=null) destination.getChildren().add(group);
		else context.canvas.getChildren().add(group);
	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		return false;
	}

	@Override
	public Group group()
	{
		return group;
	}
}
