package org.geepawhill.contentment.utility;

import javafx.scene.Node;

@FunctionalInterface
public interface NodeProcessor
{
	public boolean accept(Node node);
}