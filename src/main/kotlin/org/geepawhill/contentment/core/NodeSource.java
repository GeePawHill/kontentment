package org.geepawhill.contentment.core;

import java.util.function.Supplier;

import javafx.scene.Node;

public interface NodeSource extends Supplier<Node>
{
	
	public static NodeSource VALUE(Node node)
	{
		return ()->node;
	}

	public final static NodeSource NONE = new NodeSource()
	{

		@Override
		public Node get()
		{
			throw new RuntimeException("No NodeSource set.");
		}
	};
}
