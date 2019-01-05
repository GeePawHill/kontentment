package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.*;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.fragments.*;
import org.geepawhill.contentment.geometry.*;
import org.geepawhill.contentment.position.*;
import org.geepawhill.contentment.step.Timed;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.Group;

public class Letters implements Actor
{
	private Type letters;
	private boolean hasOval;

	private Jiggler controlJiggler;
	private Jiggler northJiggler;
	private Mark east;
	private Mark west;
	private Bezier eastHalfBezier;
	private Bezier westHalfBezier;
	protected final ScriptWorld world;
	protected final Entrance entrance;
	protected final Group group;

	public Letters(ScriptWorld world, Group destination, String source)
	{
		this.world = world;
		this.group = new Group();
		this.entrance = new Entrance(destination, group);
		this.northJiggler = new Jiggler(.5d, 6d);
		this.controlJiggler = new Jiggler(.4d, 30d);

		this.letters = new Type(group, source, Format.DEFAULT, Position.DEFAULT);
		this.east = new Mark(group, this::eastHalfPoints);
		this.west = new Mark(group, this::westHalfPoints);
	}

	public Letters withOval()
	{
		hasOval = true;
		return this;
	}

	@Override
	public Letters draw(double ms)
	{
		if(ms!=0 && ms!=1) ms = letters.source.length()*25d;
		Timed timed = new Timed(ms);
		timed.add(Timing.weighted(7), letters);
		if (hasOval)
		{
			timed.add(Timing.weighted(1.5), east);
			timed.add(Timing.weighted(1.5), west);
		}
		world.add(timed);
		return this;
	}

	@Override
	public String toString()
	{
		return "Letters [" + letters.toString() + "]";
	}

	private void setPointsIfNeeded()
	{
		if (eastHalfBezier == null)
		{
			PointPair raw = new PointPair(letters.text());
			PointPair points = raw.grow(raw.width()*0.25, raw.height()*0.15);
			eastHalfBezier = new Bezier(points.north(), controlJiggler.jiggle(points.northeast()),
					controlJiggler.jiggle(points.southeast()), points.south());
			westHalfBezier = new Bezier(points.south(), controlJiggler.jiggle(points.southwest()),
					controlJiggler.jiggle(points.northwest()), northJiggler.jiggle(points.north()));
		}
	}

	private Bezier eastHalfPoints()
	{
		setPointsIfNeeded();
		return eastHalfBezier;
	}

	public Bezier westHalfPoints()
	{
		setPointsIfNeeded();
		return westHalfBezier;
	}

	public void at(Position position)
	{
		letters.at(position);
	}

	public void format(Format format)
	{
		letters.format(format);
		east.format(format);
		west.format(format);
	}

	public void centered(double x, double y) {
		at(new Centered(x,y));
	}
	
	public void centered(Point p) {
		centered(p.x,p.y);
	}

	public Entrance entrance()
	{
		return entrance;
	}

	public Group group()
	{
		return group;
	}

}
