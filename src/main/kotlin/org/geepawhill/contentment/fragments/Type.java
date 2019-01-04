package org.geepawhill.contentment.fragments;

import org.geepawhill.contentment.core.*;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.position.Position;
import org.geepawhill.contentment.style.TypeFace;
import org.geepawhill.contentment.utility.JfxUtility;

import javafx.scene.Group;
import javafx.scene.text.Text;

/**
 * Renders some type to the screen using a Text node.
 * 
 * @author GeePaw
 *
 */
public class Type implements Fragment
{
	public final String source;
	private Text text;
	private Format format;
	private Position position;
	private String lastPartial;
	
	public Type(Group owner, String source)
	{
		this(owner,source,Format.DEFAULT,Position.DEFAULT);
	}

	public Type(Group owner, String source, Format format, Position position)
	{
		this.text = new Text();
		owner.getChildren().add(text);
		if (source == null || source.isEmpty()) source = " ";
		this.source = source;
		this.format = format;
		this.position = position;
	}

	@Override
	public boolean interpolate(Context context, double fraction)
	{
		String partialSource = source.substring(0, (int) (fraction * source.length()));
		if (!partialSource.equals(lastPartial))
		{
			lastPartial = partialSource;
			text.setText(partialSource);
		}
		return true;
	}

	@Override
	public void prepare(Context context)
	{
		text.setText(source);
		format.apply(TypeFace.FACE, text);
		format.apply(TypeFace.COLOR, text);
		PointPair dimensions = new PointPair(text.getBoundsInLocal());
		position.position(text, dimensions);
		text.setText("");
		lastPartial = "";
	}

	public void at(Position position)
	{
		this.position = position;
	}

	public void format(Format format)
	{
		this.format = format;
	}

	public Text text()
	{
		return text;
	}
}
