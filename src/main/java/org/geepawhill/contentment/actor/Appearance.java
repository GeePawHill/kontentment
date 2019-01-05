package org.geepawhill.contentment.actor;

import org.geepawhill.contentment.core.*;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.fragments.*;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.position.*;
import org.geepawhill.contentment.step.*;
import org.geepawhill.contentment.timing.Timing;

import javafx.scene.Group;
import javafx.scene.paint.Paint;

public class Appearance<ACTOR extends Actor> implements GroupSource
{
	public final ACTOR actor;
	protected final ScriptWorld world;
	protected final Entrance entrance;

	public Appearance(ScriptWorld world, ACTOR actor)
	{
		this.actor = actor;
		this.world = world;
		this.entrance = new Entrance(null,actor.group());
		actor.format(world.assumptions().format());
	}
	
	public Appearance<ACTOR> sketch()
	{
		return sketch(500d);
	}
	
	public Entrance entrance()
	{
		return entrance;
	}
	
	public Appearance<ACTOR> sketch(double time)
	{
		world.add(new Single(Timing.instant(),entrance));
		world.addActor(this);
		actor.draw(time);
		return this;
	}
	
	public Appearance<ACTOR> appear()
	{
		world.add(new Single(Timing.instant(),entrance));
		world.addActor(this);
		actor.draw(1d);
		return this;
	}

	public Appearance<ACTOR> disappear()
	{
		world.removeActor(this);
		world.add(new Single(Timing.instant(),new Exit(entrance.group())));
		return this;
	}

	public Appearance<ACTOR> called(String name)
	{
		world.callActor(name,this);
		return this;
	}
	
	public Appearance<ACTOR> fadeDown()
	{
		world.add(new Single(Timing.ms(500),new Fader(actor.group(), 0)));
		return this;
	}
	
	public Appearance<ACTOR> fadeOut()
	{
		world.removeActor(this);
		world.push(Phrase.phrase());
		world.add(new Single(Timing.ms(500d),new Fader(actor.group(), 0)));
		world.add(new Single(Timing.instant(),new Exit(entrance.group())));
		world.popAndAppend();
		return this;
	}
	
	public Appearance<ACTOR> fadeIn()
	{
		world.addActor(this);
		world.push(Phrase.phrase());
		world.add(new Single(Timing.instant(),entrance));
		world.add(new Single(Timing.instant(),new Fader(actor.group(), 0)));
		actor.draw(1d);
		world.add(new Single(Timing.ms(500d),new Fader(actor.group(),1)));
		world.popAndAppend();
		return this;
	}
	
	public Appearance<ACTOR> fadeUp()
	{
		world.add(new Single(Timing.ms(500d),new Fader(actor.group(),1)));
		return this;
	}
	
	public Appearance<ACTOR> format(Format format)
	{
		actor.format(format);
		return this;
	}
	
	public Appearance<ACTOR> at(Position position)
	{
		actor.at(position);
		return this;
	}

	public Appearance<ACTOR> centered(Point point)
	{
		at(new Centered(point));
		return this;
	}

	public Appearance<ACTOR> centered(int x, int y)
	{
		at(new Centered(x,y));
		return this;
	}

	@Override
	public Group group()
	{
		return entrance.group();
	}
	
}
