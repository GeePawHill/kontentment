package org.geepawhill.contentment.position;
import org.geepawhill.contentment.geometry.*;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.Node;

public class TopLeft implements Position
{
	
	private Point anchor;

	public TopLeft(Point anchor)
	{
		this.anchor = anchor;
	}
	
	public TopLeft(double x,double y)
	{
		this(new Point(x,y));
	}

	@Override
	public void position(Node node, PointPair dimensions)
	{
		JfxUtility.setTopAlignment(node);
		node.setTranslateX(anchor.x);
		node.setTranslateY(anchor.y);
	}

}
