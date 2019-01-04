package org.geepawhill.contentment.position;
import org.geepawhill.contentment.core.GroupSource;
import org.geepawhill.contentment.geometry.*;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.Node;

public class BelowLeft implements Position
{
	
	private GroupSource anchor;

	public BelowLeft(GroupSource anchor)
	{
		this.anchor = anchor;
	}
	
	@Override
	public void position(Node node, PointPair dimensions)
	{
		Point anchorPoint = new PointPair(anchor.group().getBoundsInParent()).southwest();
		JfxUtility.setTopAlignment(node);
		node.setTranslateX(anchorPoint.x);
		node.setTranslateY(anchorPoint.y);
	}

}
