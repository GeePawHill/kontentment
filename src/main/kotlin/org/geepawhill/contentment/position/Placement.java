package org.geepawhill.contentment.position;

import org.geepawhill.contentment.core.NodeSource;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.geometry.*;
import javafx.scene.Node;

public class Placement implements Position
{
	private PointPair area;
	private HPos horizontal;
	private NodeSource above;

	public Placement(PointPair area, NodeSource above, HPos horizontal)
	{
		this.area = area;
		this.above = above;
		this.horizontal = horizontal;
	}

	@Override
	public void position(Node node, PointPair dimensions)
	{
		PointPair remainder = area;
		if(above!=NodeSource.NONE)
		{
			double newY = new PointPair(above.get().getBoundsInParent()).south().y;
			remainder = new PointPair(remainder.from.x,newY,remainder.to.x,remainder.to.y);
		}
		switch(horizontal)
		{
		case LEFT:
			node.setTranslateX(remainder.west().x);
			break;
		case RIGHT:
			node.setTranslateX(remainder.east().x-dimensions.width());
			break;
		case CENTER:
			node.setTranslateX(remainder.centerX()-dimensions.width()/2);
		}
		
		node.setTranslateY(remainder.north().y);
		JfxUtility.setVerticalAlignment(node,VPos.TOP);
	}
}
