package org.geepawhill.contentment.rhythm

import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.contentment.test.ContentmentTest
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import tornadofx.*

class SimpleRhythmTest : ContentmentTest() {

    private val rhythm = SimpleRhythm()

    @Test
    fun newBeatIsZero() {
        assertThat(rhythm.beat).isEqualTo(0L)
    }

    @Test
    fun seekChangesClock() {
        rhythm.seekHard(100L)
        assertThat(rhythm.beat).isEqualTo(100L)
    }

    @Test
    fun doublePlayThrows() {
        assertThrows(RuntimeException::class.java) {
            rhythm.play()
            rhythm.play()
        }
    }

    @Test
    fun doublePauseThrows() {
        assertThrows(RuntimeException::class.java) { rhythm.pause() }
    }

    @Test
    fun changesBeatWhenPlayed() {
        task {
            rhythm.play()
        }
        val oldBeat = rhythm.beat
        Thread.sleep(SHORT_TIME * 2)
        assertThat(rhythm.beat).isGreaterThanOrEqualTo(oldBeat + SHORT_TIME)
    }

    @Test
    fun pauseDoesntChangeBeat() {
        val atPause = rhythm.beat
        Thread.sleep(SHORT_TIME)
        assertThat(rhythm.beat).isEqualTo(atPause)
    }

    @Test
    fun pausePauses() {
        task {
            rhythm.play()
        }
        Thread.sleep(SHORT_TIME)
        rhythm.pause()
        val atPause = rhythm.beat
        Thread.sleep(SHORT_TIME)
        assertThat(rhythm.beat).isEqualTo(atPause)
    }

    @Test
    @Throws(InterruptedException::class)
    fun playAfterPauseWorks() {
        task {
            rhythm.play()
        }
        Thread.sleep(SHORT_TIME)
        rhythm.pause()
        val atPause = rhythm.beat
        task {
            rhythm.play()
        }
        Thread.sleep(SHORT_TIME * 2)
        assertThat(rhythm.beat).isGreaterThanOrEqualTo(SHORT_TIME + atPause)
    }

    companion object {
        internal const val SHORT_TIME: Long = 20
    }
}
