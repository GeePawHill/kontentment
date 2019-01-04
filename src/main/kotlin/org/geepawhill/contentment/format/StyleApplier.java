package org.geepawhill.contentment.format;

import javafx.scene.shape.Shape;

@FunctionalInterface
public interface StyleApplier
{
	public void apply(Shape shape);
}