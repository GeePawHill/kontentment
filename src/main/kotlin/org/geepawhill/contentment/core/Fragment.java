package org.geepawhill.contentment.core;
/**
 * A Fragment of some Gesture. Sometimes gestures need multiple tiny steps to
 * complete. These are Fragments.
 * <P>
 * An example would be drawing some text with an oval around it. The underlying
 * engine isn't capable of doing this in one command, so we have to knit
 * fragments together to perform the actual gesture.
 * 
 * @author GeePaw
 */
public interface Fragment
{
	/**
	 * Get ready. Operations here are for things that 1) need to be done before the
	 * interpolation calculations can happen, but 2) have no meaninful "undo".
	 * Normally, this is a matter of doing things like gathering data for a
	 * computation used by the interpolation, such as a rectangle's coordinates.
	 * 
	 * @param context
	 */
	public void prepare(Context context);
	
	/**
	 * Do a 'partial' version of the fragment, whatever that might mean. This is the
	 * main way contentment interpolates its behavior over time.
	 * <p>
	 * The fraction is between 0.0 and 1.0. If, say, it comes in at .25, the
	 * intention is that the fragment do 1/4 of the work to be done.
	 * In normal operation, the engine guarantees that interpolate will be called at
	 * fraction 1.0, <i>but provides no such guarantee at 0.0.</i>
	 * <P>
	 * Some Fragments have no meaninful fractional behavior: setting a field to
	 * true, for instance. The interpolate method can return false if it implements
	 * such an action, in which case the engine will stop the animation and not call
	 * the interpolate method again. Otherwise, return true, and the interpolate
	 * will be called until it reaches 1.0.
	 * 
	 * @param context
	 * 
	 * @param fraction
	 * 
	 * @return <b>true</b> to continue the interpolation, <b>false</b> to indicate early completion.
	 */
	public boolean interpolate(Context context, double fraction);
}