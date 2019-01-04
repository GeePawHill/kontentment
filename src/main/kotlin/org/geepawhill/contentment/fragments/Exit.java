package org.geepawhill.contentment.fragments;

import org.geepawhill.contentment.core.*;

import javafx.scene.Group;

/**
 * Removes a group from the canvas without otherwise altering it. No-ops if
 * the group wasn't on the canvas. Throws if the group was null.
 * 
 * @author GeePaw
 *
 */
public class Exit implements Fragment
{
	private Group target;

	public Exit(Group target)
	{
		this.target = target;
	}

	@Override
	public void prepare(Context context)
	{
	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		Group parent = (Group)target.getParent();
		parent.getChildren().remove(target);
		return false;
	}

}
