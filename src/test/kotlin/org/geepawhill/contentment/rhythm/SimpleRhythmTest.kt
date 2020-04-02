package org.geepawhill.contentment.rhythm

import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.contentment.test.ContentmentTest
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class SimpleRhythmTest : ContentmentTest() {

    private val rhythm = SimpleRhythm()

    @Test
    fun newBeatIsZero() {
        assertThat(rhythm.beat()).isEqualTo(0L)
    }

    @Test
    fun seekChangesClock() {
        rhythm.seekHard(100L)
        assertThat(rhythm.beat()).isEqualTo(100L)
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
    @Throws(InterruptedException::class)
    fun changesBeatWhenPlayed() {
        rhythm.play()
        Thread.sleep(SHORT_TIME)
        assertThat(rhythm.beat()).isGreaterThanOrEqualTo(SHORT_TIME)
    }

    @Test
    @Throws(InterruptedException::class)
    fun pauseDoesntChangeBeat() {
        val atPause = rhythm.beat()
        Thread.sleep(SHORT_TIME)
        assertThat(rhythm.beat()).isEqualTo(atPause)
    }

    @Test
    @Throws(InterruptedException::class)
    fun pausePauses() {
        rhythm.play()
        Thread.sleep(SHORT_TIME)
        rhythm.pause()
        val atPause = rhythm.beat()
        Thread.sleep(SHORT_TIME)
        assertThat(rhythm.beat()).isEqualTo(atPause)
    }

    @Test
    @Throws(InterruptedException::class)
    fun playAfterPauseWorks() {
        rhythm.play()
        Thread.sleep(SHORT_TIME)
        rhythm.pause()
        val atPause = rhythm.beat()
        rhythm.play()
        Thread.sleep(SHORT_TIME)
        assertThat(rhythm.beat()).isGreaterThanOrEqualTo(SHORT_TIME + atPause)
    }

    companion object {
        internal val SHORT_TIME: Long = 20
    }
}
