package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.core.GroupSource;
import org.geepawhill.contentment.geometry.*;

public class ConnectorPoints
{
	
	private GroupSource fromGroup;
	private Point fromPoint;
	public boolean arrowheadAtFrom;
	private GroupSource toGroup;
	private Point toPoint;
	public boolean arrowheadAtTo;
	private ScriptWorld world;
	
	public ConnectorPoints(ScriptWorld world)
	{
		this.world = world;
		this.fromGroup = GroupSource.Companion.getNONE();
		this.fromPoint = new Point(0, 0);
		this.toGroup = GroupSource.Companion.getNONE();
		this.toPoint = new Point(0, 0);
	}
	
	public PointPair computeMainLine()
	{
		PointPair startLine = guessStartLine();
		PointPair main = new PointPair(adjustFromIfGroup(startLine), adjustToIfGroup(startLine));
		return main;
	}

	public void from(Point target, boolean withHead)
	{
		fromGroup = GroupSource.Companion.getNONE();
		fromPoint = target;
		arrowheadAtFrom = withHead;
	}
	
	public void from(String actorName, boolean withHead)
	{
		from(world.actor(actorName).entrance(), withHead);
	}

	public void from(GroupSource from, boolean withHead)
	{
		this.fromGroup = from;
		arrowheadAtFrom = withHead;
	}

	public void to(Point target, boolean withHead)
	{
		this.toGroup = GroupSource.Companion.getNONE();
		this.toPoint = target;
		arrowheadAtTo = withHead;
	}
	
	public void to(GroupSource to, boolean withHead)
	{
		this.toGroup = to;
		arrowheadAtTo = withHead;
	}
	
	private Point adjustToIfGroup(PointPair startLine)
	{
		if (toGroup == GroupSource.Companion.getNONE()) return startLine.getTo();
		PointPair toGrown = new PointPair(toGroup.group());
		return toGrown.quadIntersects(startLine);
	}

	private Point adjustFromIfGroup(PointPair startLine)
	{
		if (fromGroup == GroupSource.Companion.getNONE()) return startLine.getFrom();
		PointPair fromGrown = new PointPair(fromGroup.group());
		return fromGrown.quadIntersects(startLine);
	}

	private PointPair guessStartLine()
	{
		Point fromCenter = fromGroup == GroupSource.Companion.getNONE() ? fromPoint : new PointPair(fromGroup.group()).center();
		Point toCenter = toGroup == GroupSource.Companion.getNONE() ? toPoint : new PointPair(toGroup.group()).center();
		PointPair startLine = new PointPair(fromCenter, toCenter);
		return startLine;
	}
	
}