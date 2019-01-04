package org.geepawhill.contentment.core;

/**
 * A single gesture on the contentment {@link Context}. Gestures are executed in a
 * playback sequence. Actors make Gestures and add them to playback sequences to
 * make playback happen.
 * 
 * @author GeePaw
 */
public interface Gesture
{
	/**
	 * Play the gesture slowly (asynchronously) in the context using whatever internal timing
	 * values it has. When it's done playing, call the onFinished method.
	 * 
	 * @param context
	 * @param onFinished
	 */
	public void slow(Context context, OnFinished onFinished);

	/**
	 * Play the gesture instantly (synchronously) in the given
	 * {@link Context}
	 * 
	 * @param context
	 */
	public void fast(Context context);

}
