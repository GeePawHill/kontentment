package org.geepawhill.contentment.core;

/**
 * Typedef for Runnable with added standard constant NONE.
 * 
 * @author GeePaw
 *
 */
public interface OnFinished extends Runnable {
	/**
	 * A no-op Runnable
	 */
	OnFinished NONE = () -> {
	};
}
