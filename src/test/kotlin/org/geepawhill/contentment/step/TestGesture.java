package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.*;

/**
 * Handy gesture for testing plays.
 * 
 * TestGesture is a gesture that knows and exposes whether it is Undone,
 * Playing, or has Played. Most gestures have asynchronous transition, that is,
 * they go from {@linkplain org.geepawhill.contentment.step.TestGesture.State.Playing} to
 * {@link TestGesture.State.Played} independently. Since it has no way to decide
 * whether it has independently finished running, TestGesture adds the method
 * <code>Finish</code> to enable a tester to mark that. See {@link PhraseTest}
 * for a good sample usage.
 * 
 * @author GeePaw
 *
 */
public class TestGesture implements Gesture
{
	static public enum State
	{
		Undone, Playing, Played
	};

	State state;
	private OnFinished onFinished;
	private boolean autoFinish;

	public TestGesture()
	{
		this.state = State.Undone;
		this.onFinished = null;
		this.autoFinish = false;
	}

	@Override
	public void fast(Context context)
	{
		switch (state)
		{
		case Undone:
			state = State.Played;
			break;
		default:
			badChange("Fast");
		}

	}

	@Override
	public void slow(Context context, OnFinished onFinished)
	{
		this.onFinished = onFinished;
		switch (state)
		{
		case Undone:
			state = State.Playing;
			if (autoFinish)
			{
				finish(context);
			}
			break;
		default:
			badChange("Slow");
		}
	}

	public void finish(Context context)
	{
		switch (state)
		{
		case Playing:
			if (onFinished == null) throw new RuntimeException("No onFinished handler for Step.");
			onFinished.run();
			state = State.Played;
			break;
		default:
			badChange("Finish");
		}
	}

	private void badChange(String change)
	{
		throw new RuntimeException(change + " called on " + state + " Step.");
	}

}
