package org.geepawhill.contentment.rhythm

interface RhythmListener {
    fun pause()
    fun play()
    fun seek(ms: Long)
    fun frame()
}

class RhythmListeners {
    private val listeners = mutableListOf<RhythmListener>()

    fun add(listener: RhythmListener) {
        if (listeners.contains(listener)) throw RuntimeException("Listener added twice.")
        listeners.add(listener)
    }

    fun remove(listener: RhythmListener) {
        if (!listeners.remove(listener)) throw RuntimeException("Attempt to remove listener failed.")
    }

    fun notify(call: RhythmListener.() -> Unit) {
        for (listener in listeners) {
            listener.call()
        }
    }
}