package org.geepawhill.contentment.position;
import org.geepawhill.contentment.core.GroupSource;
import org.geepawhill.contentment.geometry.*;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.Node;

public class AboveCenter implements Position
{
	
	private GroupSource anchor;
	private double offset;

	public AboveCenter(GroupSource anchor,double offset)
	{
		this.anchor = anchor;
		this.offset = offset;
	}
	
	public AboveCenter(GroupSource anchor)
	{
		this(anchor,0d);
	}
	
	@Override
	public void position(Node node, PointPair dimensions)
	{
		Point anchorPoint = new PointPair(anchor.group().getBoundsInParent()).north();
		JfxUtility.setTopAlignment(node);
		node.setTranslateX(anchorPoint.x-dimensions.width()/2d);
		node.setTranslateY(anchorPoint.y-dimensions.height()+offset);
	}

}
