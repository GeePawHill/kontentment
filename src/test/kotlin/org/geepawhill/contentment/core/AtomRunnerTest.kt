package org.geepawhill.contentment.core

import javafx.application.Platform
import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.contentment.test.ContentmentTest
import org.geepawhill.contentment.test.TestAtom
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import tornadofx.*

class AtomRunnerTest : ContentmentTest() {
    @Test
    @RepeatedTest(1000)
    fun atZeroTime() {
        assertThat(Platform.isFxApplicationThread()).isFalse()
        val atom = TestAtom()
        val fragment = FragmentTransition(0L, atom, context, OnFinished.NOOP)
        task {
            fragment.play()
        }
        Thread.sleep(200)
        assertThat(atom.fractions).contains(0.0, 1.0)
    }

    @Test
    fun atSmallTime() {
        assertThat(Platform.isFxApplicationThread()).isFalse()
        val atom = TestAtom()
        runner.play(100L, atom)
        assertThat(atom.fractions).contains(0.0, 1.0)
        assertThat(atom.fractions.size).isGreaterThan(2)
    }
}
