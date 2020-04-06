package org.geepawhill.contentment.rhythm

interface RhythmSyncer {
    fun pause()
    fun play()
    fun seek(ms: Long)
}

class RhythmSyncerList() {
    private val listeners = mutableListOf<RhythmSyncer>()
    fun add(syncer: RhythmSyncer) {
        if (listeners.contains(syncer)) throw RuntimeException("Listener added twice.")
        listeners.add(syncer)
    }

    fun remove(syncer: RhythmSyncer) {
        if (!listeners.remove(syncer)) throw RuntimeException("Attempt to remove listener failed.")
    }

    fun notify(call: RhythmSyncer.() -> Unit) {
        for (listener in listeners) {
            listener.call()
        }
    }
}