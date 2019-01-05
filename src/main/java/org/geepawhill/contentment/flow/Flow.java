package org.geepawhill.contentment.flow;

import java.util.Vector;

import org.geepawhill.contentment.actor.*;
import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.position.TopLeft;
import org.geepawhill.contentment.style.TypeFace;

import javafx.scene.Group;
import javafx.scene.text.Text;

public class Flow
{
	public static class Line
	{
		public String text;
		public Color color;
		public Size size;
		public PointPair layout;
	}

	private final FormatTable table;
	private final Vector<Line> lines;
	private final Text sizer;
	private ScriptWorld world;
	private final PointPair area;

	public Flow(ScriptWorld world, PointPair area)
	{
		this.world = world;
		this.area = area;
		lines = new Vector<>();
		sizer = new Text();
		table = new FormatTable();
	}

	public Vector<Line> lines()
	{
		return lines;
	}

	public void load(String source)
	{
		lines.clear();
		String[] markups = source.split("\n");
		for (String markup : markups)
		{
			Line line = new Line();
			line.text = markup.substring(2);
			line.color = chooseColor(markup);
			line.size = chooseSize(markup);
			lines.add(line);
		}
		layout();
	}
	
	public Appearance<Letters> letters(int i)
	{
		Line line = lines.get(i);
		TopLeft position = new TopLeft(line.layout.from);
		Letters result = new Letters(world,new Group(), line.text);
		return new Appearance<Letters>(world, result).format(table.get(line.size, line.color)).at(position);
	}

	private void layout()
	{
		double lastEndY = area.from.y;
		for(Line line : lines)
		{
			Format format = table.get(line.size,line.color);
			format.apply(TypeFace.FACE, sizer);
			sizer.setText(line.text);
			PointPair layout = new PointPair(sizer.getLayoutBounds());
			double newLastEndY = lastEndY+1+layout.height();
			line.layout = new PointPair(area.from.x,lastEndY+1,layout.width(),newLastEndY);
			lastEndY = newLastEndY;
		}
	}
	
	private Color chooseColor(String substring)
	{
		char colorChar = substring.toLowerCase().charAt(0);
		switch (colorChar)
		{
		default:
		case 'P':
			return Color.Primary;
		case 's':
			return Color.Secondary;
		case 'e':
			return Color.Emphatic;
		case 't':
			return Color.Tertiary;
		}
	}

	private Size chooseSize(String substring)
	{
		char sizeChar = substring.toLowerCase().charAt(1);
		switch (sizeChar)
		{
		default:
		case 'n':
			return Size.Normal;
		case 'j':
			return Size.Jumbo;
		case 's':
			return Size.Small;
		}
	}
	
	public int size()
	{
		return lines.size();
	}

}
