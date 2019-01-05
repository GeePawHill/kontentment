package org.geepawhill.contentment.fragments;

import org.geepawhill.contentment.core.*;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.BezierSource;
import org.geepawhill.contentment.style.Frames;

import javafx.scene.Group;
import javafx.scene.shape.Path;

/**
 * Animates a single (bezier) line in a given format to the screen using a Path
 * node.
 * 
 * @author GeePaw
 *
 */
public class Mark implements Fragment
{
	private final Path path;
	private final BezierSource source;
	private Format format;

	public Mark(Group owner, BezierSource source)
	{
		this.source = source;
		this.path = new Path();
		owner.getChildren().add(path);
		this.format = Format.DEFAULT;
	}

	@Override
	public void prepare(Context context)
	{
		format.apply(Frames.KEY, path);
		interpolate(context, 0d);
	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		source.get().splitToPath(fraction, path);
//		new BezierSplit(fraction, source.get()).setPathToBefore(path);
		return true;
	}

	public void format(Format format)
	{
		this.format = format;
	}
}
