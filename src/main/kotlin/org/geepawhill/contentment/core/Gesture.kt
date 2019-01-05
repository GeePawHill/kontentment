package org.geepawhill.contentment.core

/**
 * A single gesture on the contentment [Context]. Gestures are executed in a
 * playback sequence. Actors make Gestures and add them to playback sequences to
 * make playback happen.
 *
 * @author GeePaw
 */
interface Gesture {
    /**
     * Play the gesture slowly (asynchronously) in the context using whatever internal timing
     * values it has. When it's done playing, call the onFinished method.
     *
     * @param context
     * @param onFinished
     */
    fun slow(context: Context, onFinished: OnFinished)

    /**
     * Play the gesture instantly (synchronously) in the given
     * [Context]
     *
     * @param context
     */
    fun fast(context: Context)

}
