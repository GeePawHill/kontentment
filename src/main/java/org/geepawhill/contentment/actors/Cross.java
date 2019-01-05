package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.*;
import org.geepawhill.contentment.core.GroupSource;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.fragments.*;
import org.geepawhill.contentment.geometry.*;
import org.geepawhill.contentment.position.Position;
import org.geepawhill.contentment.step.Timed;
import org.geepawhill.contentment.style.Frames;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.Group;
import javafx.scene.paint.Color;

public class Cross implements Actor
{

	private GroupSource target;
	private Mark leftToRight;
	private Mark rightToLeft;
	private double xsize;
	private double ysize;
	private Point offset;
	private Format crossFormat;
	protected final ScriptWorld world;
	protected final Entrance entrance;
	protected final Group group;
	
	public Cross(ScriptWorld world, Group destination, Appearance<? extends Actor> target, double size)
	{
		this(world,destination,target.entrance(),size, size, new Point(0,0));
	}

	public Cross(ScriptWorld world, Group destination, GroupSource target, double xsize, double ysize, double xoffset, double yoffset)
	{
		this(world,destination,target,xsize,ysize, new Point(xoffset,yoffset));
	}

	public Cross(ScriptWorld world, Group destination, GroupSource target, double xsize, double ysize, Point offset)
	{
		this.world = world;
		this.group = new Group();
		this.entrance = new Entrance(group);
		this.target = target;
		this.xsize = xsize;
		this.ysize = ysize;
		this.offset = offset;
		crossFormat = new Format(Frames.frame(Color.RED, 7d, .7d));
		leftToRight = new Mark(group,this::leftToRightBezier);
		rightToLeft = new Mark(group,this::rightToLeftBezier);
	}

	@Override
	public Cross draw(double ms)
	{
		leftToRight.format(crossFormat);
		rightToLeft.format(crossFormat);
		Timed timed = new Timed(ms);
		timed.add(Timing.weighted(.5),leftToRight);
		timed.add(Timing.weighted(.5),rightToLeft);
		world.add(timed);
		return this;
	}
	
	private Bezier leftToRightBezier()
	{
		double xadditive = xsize/2d;
		double yadditive = ysize/2d;
		Point center = new PointPair(target.group()).center().add(offset);
		return new Bezier(new Point(center.x-xadditive, center.y-yadditive),new Point(center.x+xadditive,center.y+yadditive));
	}

	
	private Bezier rightToLeftBezier()
	{
		double xadditive = xsize/2d;
		double yadditive = ysize/2d;
		Point center = new PointPair(target.group()).center().add(offset);
		return new Bezier(
				new Point(center.x+xadditive, center.y-yadditive), new Point(center.x-xadditive, center.y+yadditive));
	}

	@Override
	public void format(Format format)
	{
	}

	@Override
	public void at(Position position)
	{
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
