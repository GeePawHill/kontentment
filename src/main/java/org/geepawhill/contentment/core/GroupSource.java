package org.geepawhill.contentment.core;

import javafx.scene.Group;

public interface GroupSource 
{
	Group group();

	GroupSource NONE = () -> {
		throw new NoGroupSource();
	};
	
	class NoGroupSource extends RuntimeException
	{
		public NoGroupSource()
		{
			super("GroupSource has no group.");
		}
	}

}
