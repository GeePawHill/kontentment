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
		if(above!= NodeSource.Companion.getNONE())
		{
			double newY = new PointPair(above.get().getBoundsInParent()).south().getY();
			remainder = new PointPair(remainder.getFrom().getX(),newY, remainder.getTo().getX(), remainder.getTo().getY());
		}
		switch(horizontal)
		{
		case LEFT:
			node.setTranslateX(remainder.west().getX());
			break;
		case RIGHT:
			node.setTranslateX(remainder.east().getX() -dimensions.width());
			break;
		case CENTER:
			node.setTranslateX(remainder.centerX()-dimensions.width()/2);
		}
		
		node.setTranslateY(remainder.north().getY());
		JfxUtility.setVerticalAlignment(node,VPos.TOP);
	}
}
