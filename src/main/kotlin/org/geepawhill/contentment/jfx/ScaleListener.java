package org.geepawhill.contentment.jfx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaView;
import javafx.scene.transform.Scale;

public class ScaleListener implements ChangeListener<Number>
{
	private final Pane host;
	private Group child;
	private MediaView media;

	public ScaleListener(Pane host, Group canvas, MediaView media)
	{
		this.host = host;
		this.child = canvas;
		this.media = media;
	}

	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
	{
		final double newWidth = host.getWidth();
		media.setFitWidth(newWidth);
		final double newHeight = host.getHeight();
		double scaleFactor = newWidth / newHeight > 16d / 9d ? newHeight / 900d : newWidth / 1600d;
		Scale newScale = new Scale(scaleFactor, scaleFactor);
		newScale.setPivotX(0);
		newScale.setPivotY(0);
		child.getTransforms().setAll(newScale);
	}
}
