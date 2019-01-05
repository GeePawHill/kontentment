package org.geepawhill.contentment.position;
import org.geepawhill.contentment.geometry.*;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.Node;

public class TopRight implements Position
{
	
	private Point anchor;

	public TopRight(Point anchor)
	{
		this.anchor = anchor;
	}
	
	public TopRight(double x,double y)
	{
		this(new Point(x,y));
	}

	@Override
	public void position(Node node, PointPair dimensions)
	{
		JfxUtility.setTopAlignment(node);
		node.setTranslateX(anchor.x-dimensions.width());
		node.setTranslateY(anchor.y);
	}

}
