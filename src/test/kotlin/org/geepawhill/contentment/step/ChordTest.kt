package org.geepawhill.contentment.step

import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.contentment.core.Context
import org.geepawhill.contentment.core.OnFinished
import org.junit.jupiter.api.Test
import step.Phrase

class ChordTest {
    private val onlyOne: Phrase = Phrase.chord()
    private val both: Phrase = Phrase.chord()
    private val context: Context = Context()
    private val one: TestGesture = TestGesture()
    private val two: TestGesture = TestGesture()
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
    fun fastOne() {
        onlyOne.fast(context)
        assertPlayed(one)
    }

    @Test
    fun fastBoth() {
        both.fast(context)
        assertPlayed(one)
        assertPlayed(two)
    }

    @Test
    fun slowOne() {
        onlyOne.slow(context, recordFinish)
        assertPlaying(one)
        one.finish()
        assertPlayed(one)
        assertThat(gotFinish).isTrue()
    }

    @Test
    fun slowBoth() {
        both.slow(context, recordFinish)
        assertPlaying(one)
        assertPlaying(two)
        one.finish()
        assertThat(gotFinish).isFalse()
        two.finish()
        assertPlayed(one)
        assertPlayed(two)
        assertThat(gotFinish).isTrue()
    }

    private fun assertPlayed(Step: TestGesture) {
        assertThat(Step.state).isEqualTo(TestGesture.State.Played)
    }

    private fun assertPlaying(Step: TestGesture) {
        assertThat(Step.state).isEqualTo(TestGesture.State.Playing)
    }
}
