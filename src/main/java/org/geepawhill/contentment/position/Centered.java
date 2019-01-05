package org.geepawhill.contentment.position;
import org.geepawhill.contentment.core.GroupSource;
import org.geepawhill.contentment.geometry.*;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.Node;

public class Centered implements Position
{
	
	private GroupSource actor;
	private Point anchor;

	public Centered(GroupSource actor)
	{
		this(actor,null);
	}
	
	public Centered(Point anchor)
	{
		this(null,anchor);
	}
	
	public Centered(GroupSource actor,Point anchor)
	{
		this.actor = actor;
		this.anchor = anchor;
	}
	
	public Centered(double x,double y)
	{
		this(new Point(x,y));
	}

	@Override
	public void position(Node node, PointPair dimensions)
	{
		JfxUtility.setTopAlignment(node);
		if(actor!=null) anchor = new PointPair(actor.group().getBoundsInParent()).center();
		node.setTranslateX(anchor.x-dimensions.width()/2d);
		node.setTranslateY(anchor.y-dimensions.height()/2d);
	}

}
