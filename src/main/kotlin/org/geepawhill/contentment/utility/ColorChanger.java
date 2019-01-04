package org.geepawhill.contentment.utility;

import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class ColorChanger implements NodeProcessor
{
	public Paint result;
	private Paint paint;

	public ColorChanger(Paint paint)
	{
		result = null;
		this.paint = paint;
	}

	@Override
	public boolean accept(Node node)
	{
		if (node instanceof Shape)
		{
			Shape shape = (Shape) node;
			if (result == null) result = shape.getStroke();
			shape.setStroke(paint);
		}
		if (node instanceof Text)
		{
			Text text = (Text) node;
			text.setFill(paint);
		}
		return true;
	}
}