package org.geepawhill.contentment.rhythm

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class RhythmSyncerTest {
    private val list = RhythmSyncerList()

    class DumbSyncer : RhythmSyncer {
        var paused = 0
        var played = 0
        var seeked = 0
        override fun pause() {
            paused += 1
        }

        override fun play() {
            played += 1
        }

        override fun seek(ms: Long) {
            seeked += 1
        }

    }

    @Test
    fun `add adds`() {
        val listener = DumbSyncer()
        list.add(listener)
        list.notify { pause() }
        assertThat(listener.paused).isEqualTo(1)
    }

    @Test
    fun `multiple observers work`() {
        val listenerOne = DumbSyncer()
        list.add(listenerOne)
        val listenerTwo = DumbSyncer()
        list.add(listenerTwo)
        list.notify { pause() }
        assertThat(listenerOne.paused).isEqualTo(1)
        assertThat(listenerTwo.paused).isEqualTo(1)
    }

    @Test
    fun `remove removes`() {
        val listener = DumbSyncer()
        list.add(listener)
        list.notify { pause() }
        list.remove(listener)
        list.notify { pause() }
        assertThat(listener.paused).isEqualTo(1)
    }

    @Test
    fun `remove throws on not found`() {
        assertThrows<RuntimeException> {
            val listener = DumbSyncer()
            list.remove(listener)
        }
    }

    @Test
    fun `can't add twice`() {
        assertThrows<RuntimeException> {
            val listener = DumbSyncer()
            list.add(listener)
            list.add(listener)
        }
    }

}