package org.geepawhill.contentment.style;

import org.geepawhill.contentment.format.*;

import javafx.scene.paint.*;
import javafx.scene.shape.Shape;

public class Frames
{
	public static final String KEY = "Frame";

	public static Style frame(Paint stroke, Double width, Double opacity)
	{
		return frame(stroke, Color.TRANSPARENT, width, opacity);
	}

	public static Style frame(Paint stroke, Double width, Double opacity, Dash dash)
	{
		return frame(stroke, Color.TRANSPARENT, width, opacity, dash);
	}

	public static Style frame(Paint stroke, Paint fill, Double width, Double opacity)
	{
		return frame(stroke, fill, width, opacity, Dash.solid());
	}

	public static Style frame(Paint stroke, Paint fill, Double width, Double opacity, Dash dash)
	{
		StyleApplier applier = new StyleApplier()
		{
			@Override
			public void apply(Shape shape)
			{
				shape.setStroke(stroke);
				shape.setFill(fill);
				shape.setStrokeWidth(width);
				shape.setOpacity(opacity);
				shape.getStrokeDashArray().clear();
				shape.getStrokeDashArray().addAll(dash.array);
			}
		};
		String value = "Frame: " + stroke.toString() + " Fill: " + fill.toString() + " Width: " + width + " Opacity: " + opacity
				+ " Dash: " + dash;
		return new Style(KEY, applier, value);

	}

	public static Style unspecified()
	{
		return frame(Color.WHITE, Color.TRANSPARENT, 1d, 1d);
	}

}
