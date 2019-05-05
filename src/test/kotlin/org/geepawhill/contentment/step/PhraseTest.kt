package org.geepawhill.contentment.step

import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.contentment.core.OnFinished
import org.geepawhill.contentment.test.ContentmentTest
import org.junit.jupiter.api.Test

/**
 * PhraseTest tests that phrases can play their gestures fast (synchronously) or
 * slow (asynchronously).
 *
 * @author GeePaw
 */
class PhraseTest : ContentmentTest() {
    private val onlyOne: Phrase = Phrase.phrase()
    private val one: TestGesture = TestGesture()
    private val two: TestGesture = TestGesture()
    private val both: Phrase = Phrase.phrase()
    private var gotFinish: Boolean = false
    private val recordFinish: OnFinished = object : OnFinished {
        override fun run() {
            gotFinish = true
        }
    }

    init {
        onlyOne.add(one)
        both.add(one)
        both.add(two)
    }

    @Test
    fun fastPlaysOne() {
        onlyOne.fast(context)
        assertPlayed(one)
    }

    @Test
    fun fastPlaysTwo() {
        both.fast(context)
        assertPlayed(one)
        assertPlayed(two)
    }

    @Test
    fun slowPlaysOne() {
        onlyOne.slow(context, recordFinish)
        assertPlaying(one)
        one.finish()
        assertPlayed(one)
        assertThat(gotFinish).isTrue()
    }

    @Test
    fun slowPlaysBoth() {
        both.slow(context, recordFinish)
        assertPlaying(one)
        assertUndone(two)
        one.finish()
        assertPlayed(one)
        assertPlaying(two)
        two.finish()
        assertPlayed(two)
        assertThat(gotFinish).isTrue()
    }

    private fun assertPlayed(Step: TestGesture) {
        assertThat(Step.state).isEqualTo(TestGesture.State.Played)
    }

    private fun assertUndone(Step: TestGesture) {
        assertThat(Step.state).isEqualTo(TestGesture.State.Undone)
    }

    private fun assertPlaying(Step: TestGesture) {
        assertThat(Step.state).isEqualTo(TestGesture.State.Playing)
    }
}
