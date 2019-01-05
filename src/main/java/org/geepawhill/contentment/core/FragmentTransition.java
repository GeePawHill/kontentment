package org.geepawhill.contentment.core;

import javafx.animation.Transition;
import javafx.util.Duration;

public class FragmentTransition extends Transition
{
	private final long ms;
	private final Fragment fragment;
	private final Context context;
	private final OnFinished onFinished;

	/**
	 * Create a FragmentTransition that lasts ms long (minimum 1), playing the
	 * Fragment against the Context, then calling the OnFinished callback.
	 * 
	 * @param ms -- desired milliseconds
	 * @param fragment -- fragment to interpolate across the time
	 * @param context -- context in which to do the fragment
	 * @param onFinished -- callback for when it's done
	 */
	public FragmentTransition(long ms, Fragment fragment, Context context, OnFinished onFinished)
	{
		this.ms = ms > 0 ? ms : 1;
		this.fragment = fragment;
		this.context = context;
		this.onFinished = onFinished;
	}

	/**
	 * Override the animation's play to also set the two key parameters from the
	 * constructor and guarantee that the prepare method is called.
	 *
	 * @see javafx.animation.Animation#play()
	 */
	@Override
	public void play()
	{
		setCycleDuration(Duration.millis(ms));
		setOnFinished((event) -> onFinished.run());
		fragment.prepare(context);
		super.play();
	}

	/**
	 * Overrides the base classes interpolate to force the fragment to draw,
	 * possibly ending the animation. <br />
	 * 
	 * @see javafx.animation.Transition#interpolate(double)
	 */
	@Override
	protected void interpolate(double fraction)
	{
		if (!fragment.interpolate(context, fraction)) finishEarly();
	}

	private void finishEarly()
	{
		// Note: OnFinished is called automatically by super when there's no call to #stop.
		stop();
		onFinished.run();
	}
}
