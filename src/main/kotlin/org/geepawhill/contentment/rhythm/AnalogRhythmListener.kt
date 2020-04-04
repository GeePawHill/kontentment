package org.geepawhill.contentment.rhythm

interface AnalogRhythmListener {
    fun pause()
    fun play()
    fun seek(ms: Long)
}

class AnalogListenerList() {
    private val listeners = mutableListOf<AnalogRhythmListener>()
    fun add(listener: AnalogRhythmListener) {
        if (listeners.contains(listener)) throw RuntimeException("Listener added twice.")
        listeners.add(listener)
    }

    fun remove(listener: AnalogRhythmListener) {
        if (!listeners.remove(listener)) throw RuntimeException("Attempt to remove listener failed.")
    }

    fun notify(call: AnalogRhythmListener.() -> Unit) {
        for (listener in listeners) {
            listener.call()
        }
    }
}