package org.geepawhill.contentment.style;

import org.geepawhill.contentment.format.*;

import javafx.scene.paint.*;
import javafx.scene.shape.Shape;
import javafx.scene.text.*;

public class TypeFace
{
	public static final String FACE = "TextFont";
	public static final String COLOR = "TextColor";

	static public Style font(Font font, Double width, Double opacity)
	{
		StyleApplier applier = new StyleApplier()
		{
			@Override
			public void apply(Shape shape)
			{
				if (shape instanceof Text)
				{
					Text text = (Text) shape;
					text.setFont(font);
					text.setStrokeWidth(width);
					text.setOpacity(opacity);
				}
			}
		};
		return new Style(FACE, applier, font.getFamily() + " " + font.getSize());
	}

	public static Style smallHand()
	{
		return font(new Font("Buxton Sketch", 22d), 1d, 1d);
	}

	public static Style mediumHand()
	{
		return font(new Font("GoodDog", 50d), 2d, 1d);
	}

	public static Style largeHand()
	{
		return font(new Font("GoodDog", 60d), 2d, 1d);
	}

	public static Style mediumSans()
	{
		return font(new Font("Arial", 30d), 1d, .8d);
	}

	public static Style smallSans()
	{
		return font(new Font("Arial", 15d), 1d, .8d);
	}

	public static Style smallFixed()
	{
		return font(new Font("Consolas", 15d), 1d, 1d);
	}

	public static Style color(Paint both, Double opacity)
	{
		return TypeFace.color(both, both, opacity);
	}

	public static Style color(Paint stroke, Paint fill, Double opacity)
	{
		StyleApplier applier = new StyleApplier()
		{
			@Override
			public void apply(Shape shape)
			{
				shape.setStroke(stroke);
				shape.setFill(fill);
				shape.setOpacity(opacity);
			}
		};
		String value = "Stroke: " + stroke.toString() + " Fill: " + fill.toString() + " Opacity: " + opacity;
		return new Style(COLOR, applier, value);

	}

	public static Style white()
	{
		return color(Color.WHITE, 1d);
	}
}
