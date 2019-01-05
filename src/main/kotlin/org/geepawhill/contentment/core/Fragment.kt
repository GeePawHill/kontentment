package org.geepawhill.contentment.core

/**
 * A Fragment of some Gesture. Sometimes gestures need multiple tiny steps to
 * complete. These are Fragments.
 * <P>
 * An example would be drawing some text with an oval around it. The underlying
 * engine isn't capable of doing this in one command, so we have to knit
 * fragments together to perform the actual gesture.
 *
 * @author GeePaw
</P> */
interface Fragment {
    /**
     * Get ready. Operations here are for things that 1) need to be done before the
     * interpolation calculations can happen, but 2) have no meaninful "undo".
     * Normally, this is a matter of doing things like gathering data for a
     * computation used by the interpolation, such as a rectangle's coordinates.
     *
     * @param context
     */
    fun prepare(context: Context)

    /**
     * Do a 'partial' version of the fragment, whatever that might mean. This is the
     * main way contentment interpolates its behavior over time.
     *
     *
     * The fraction is between 0.0 and 1.0. If, say, it comes in at .25, the
     * intention is that the fragment do 1/4 of the work to be done.
     * In normal operation, the engine guarantees that interpolate will be called at
     * fraction 1.0, *but provides no such guarantee at 0.0.*
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
     * @return **true** to continue the interpolation, **false** to indicate early completion.
    </P> */
    fun interpolate(context: Context, fraction: Double): Boolean
}