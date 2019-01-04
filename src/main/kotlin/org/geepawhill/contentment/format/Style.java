package org.geepawhill.contentment.format;

import javafx.scene.shape.Shape;

public class Style
{
	private final StyleApplier applier;

	private final String key;
	private final String value;

	public Style(String key, StyleApplier applier, String value)
	{
		this.key = key;
		this.applier = applier;
		this.value = value;
	}

	@Override
	public String toString()
	{
		return key + " (" + value + ")";
	}

	public void apply(Shape shape)
	{
		applier.apply(shape);
	}

	public String key()
	{
		return key;
	}
}
