package org.geepawhill.contentment.actor;

import java.util.*;

import org.geepawhill.contentment.core.Gesture;
import org.geepawhill.contentment.format.Assumptions;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.step.*;

public class ScriptWorld
{
	private Stack<Phrase> working;
	private final HashMap<String,Appearance<? extends Actor>> namedActors;
	private final Vector<Appearance<? extends Actor>> actors;
	private final Assumptions assumptions;
	private final Random random;

	public ScriptWorld()
	{
		working = new Stack<>();
		namedActors = new HashMap<>();
		assumptions = new Assumptions();
		random = new Random();
		actors = new Vector<>();
	}
	
	public void add(Gesture step)
	{
		getWorking().add(step);
	}
	
	public Point jiggle(Point point, double probability, double variance)
	{
		double newX = point.x;
		double newY = point.y;
		if (random.nextDouble() < probability)
		{
			double sign = random.nextDouble() > .5 ? -1 : +1;
			double change = random.nextDouble() * variance;
			newX += sign * change;
		}
		if (random.nextDouble() < probability)
		{
			double sign = random.nextDouble() > .5 ? -1 : +1;
			double change = random.nextDouble() * variance;
			newY += sign * change;
		}
		return new Point(newX, newY);
	}

	
	public boolean flip(double truePercentage)
	{
		return random.nextDouble() < truePercentage/100d;
	}
	
	public double nextDouble()
	{
		return random.nextDouble();
	}
	
	public void push(Phrase phrase)
	{
		working.push(phrase);
	}
	
	public Phrase pop()
	{
		return working.pop();
	}
	
	public void popAndAppend()
	{
		Phrase popped = pop();
		add(popped);
	}
	
	public Appearance<? extends Actor> actor(String actor)
	{
		if(!namedActors.containsKey(actor)) throw new RuntimeException("Can't find actor: ["+actor+"]");
		return namedActors.get(actor);
	}

	public void callActor(String name, Appearance<? extends Actor> actor)
	{
		namedActors.put(name, actor);
	}

	private Phrase getWorking()
	{
		return working.peek();
	}

	public Assumptions assumptions()
	{
		return assumptions;
	}

	public void addActor(Appearance<? extends Actor> appearance)
	{
		actors.add(appearance);
	}
	
	public void removeActor(Appearance<? extends Actor> appearance)
	{
		actors.remove(appearance);
	}

	public Vector<Appearance<? extends Actor>> entrances()
	{
		return actors;
	}
}
