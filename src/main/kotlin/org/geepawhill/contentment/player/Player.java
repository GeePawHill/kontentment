package org.geepawhill.contentment.player;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.rhythm.Rhythm;

import javafx.beans.property.*;

public class Player
{
	private int position;
	private Context context;
	
	private final SimpleObjectProperty<Script> scriptProperty;
	private final SimpleObjectProperty<PlayerState> stateProperty;
	private final SimpleBooleanProperty atStartProperty;
	private final SimpleBooleanProperty atEndProperty;

	public Player()
	{
		this.atStartProperty = new SimpleBooleanProperty(true);
		this.atEndProperty = new SimpleBooleanProperty(false);
		this.stateProperty = new SimpleObjectProperty<>(PlayerState.Stepping);
		this.scriptProperty = new SimpleObjectProperty<>(new Script());
		this.context = new Context();
		this.position = 0;
	}
	
	public Context context()
	{
		return context;
	}
	
	public BooleanProperty atStartProperty()
	{
		return atStartProperty;
	}

	public BooleanProperty atEndProperty()
	{
		return atEndProperty;
	}

	public boolean atStart()
	{
		return atStartProperty.get();
	}

	public boolean atEnd()
	{
		return atEndProperty.get();
	}

	public void load(Script script)
	{
		this.scriptProperty.set(script);
		this.setPosition(0);
		getRhythm().seekHard((long) 0);
		stateProperty.set(PlayerState.Stepping);
		context.setRhythm(script.rhythm());
	}

	public int position()
	{
		return position;
	}

	public void forward()
	{
		mustBeStepping();
		if (!atEnd())
		{
			nextSync().phrase.fast(context);
			setPosition(position() + 1);
			if (atEnd()) getRhythm().seekHard(Rhythm.MAX);
			else getRhythm().seekHard(nextSync().target);
		}
	}

	private Keyframe nextSync()
	{
		return getSync(position());
	}

	private Keyframe getSync(int sync)
	{
		return getScript().get(sync);
	}

	public void backward()
	{
		mustBeStepping();
		if(!atStart())
		{
			context.wipe();
			int newPosition = position-1;
			setPosition(0);
			while(position!=newPosition)
			{
				forward();
			}
			if (atEnd()) getRhythm().seekHard(Rhythm.MAX);
			else getRhythm().seekHard(nextSync().target);
		}
//		mustBeStepping();
//		if (!atStart())
//		{
//			setPosition(position() - 1);
//			nextSync().phrase.undo(context);
//			getRhythm().seekHard(nextSync().target);
//		}
	}

	public PlayerState getState()
	{
		return stateProperty.get();
	}

	public void playOne()
	{
		mustBeStepping();
		if (!atEnd())
		{
			stateProperty.set(PlayerState.Playing);
			getRhythm().play();
			new BeatWaiter(context, nextSync().phrase, this::isPlayOneDone, this::newPlayOneFinished).play();
		}
	}

	public Boolean isPlayOneDone()
	{
		if (position < getScript().size() - 1)
		{
			if (getRhythm().beat() >= getSync(position + 1).target) return true;
			else return false;
		}
		else
		{
			return getRhythm().isAtEnd();
		}
	}

	public void newPlayOneFinished()
	{
		getRhythm().pause();
		stateProperty.set(PlayerState.Stepping);
		setPosition(position() + 1);
		if (atEnd()) getRhythm().seekHard(Rhythm.MAX);
		else getRhythm().seekHard(nextSync().target);
	}
	
	public void newPlayFinished()
	{
		setPosition(position()+1);
		if(!atEnd())
		{
			new BeatWaiter(context, nextSync().phrase, this::isPlayOneDone, this::newPlayFinished).play();
		}
		else
		{
			getRhythm().pause();
			stateProperty.set(PlayerState.Stepping);
		}
	}

	private void mustBeStepping()
	{
		if (getState() != PlayerState.Stepping) throw new RuntimeException("Playing when should be Stepping.");
	}

	public void play()
	{
		mustBeStepping();
		if (!atEnd())
		{
			stateProperty.set(PlayerState.Playing);
			getRhythm().play();
			new BeatWaiter(context, nextSync().phrase, this::isPlayOneDone, this::newPlayFinished).play();
		}
	}

	public void end()
	{
		mustBeStepping();
		while (position() < getScript().size())
		{
			forward();
		}
	}

	public void start()
	{
		mustBeStepping();
		while (position() != 0)
		{
			backward();
		}
	}

	public void ultimate()
	{
		mustBeStepping();
		end();
		backward();
	}
	
	public void penultimate()
	{
		mustBeStepping();
		end();
		backward();
		backward();
	}

	public Rhythm getRhythm()
	{
		return getScript().rhythm();
	}

	public void setPosition(int position)
	{
		this.position = position;
		atStartProperty.set(position == 0);
		atEndProperty.set(position == getScript().size());
	}


	public ObjectProperty<PlayerState> stateProperty()
	{
		return stateProperty;
	}
	
	public ObjectProperty<Script> scriptProperty()
	{
		return scriptProperty;
	}

	public Script getScript()
	{
		return scriptProperty.get();
	}
}
