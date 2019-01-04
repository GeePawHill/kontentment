package org.geepawhill.contentment.geometry;

import java.util.function.Supplier;

public interface BezierSource extends Supplier<Bezier>
{

	static BezierSource value(Bezier bezier)
	{
		return () -> bezier;
	}
}
