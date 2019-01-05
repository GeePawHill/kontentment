package org.geepawhill.contentment.position;

import org.geepawhill.contentment.geometry.*;

import javafx.scene.Node;

public interface Position
{

	Position DEFAULT = new Centered(ViewPort.Companion.getCENTER());

	void position(Node node, PointPair dimensions);
	
}