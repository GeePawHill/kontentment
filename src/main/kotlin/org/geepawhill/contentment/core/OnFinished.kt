package org.geepawhill.contentment.core

/**
 * Typedef for Runnable with added standard constant NONE.
 *
 * @author GeePaw
 */
interface OnFinished : Runnable {

    companion object {
        val NOOP = object : OnFinished {
            override fun run() {
            }
        }
    }
}
