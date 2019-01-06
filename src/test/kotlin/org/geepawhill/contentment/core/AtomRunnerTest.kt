package org.geepawhill.contentment.core

import javafx.application.Platform
import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.contentment.test.ContentmentTest
import org.geepawhill.contentment.test.TestAtom
import org.junit.jupiter.api.Test

class AtomRunnerTest : ContentmentTest() {
    @Test
    fun atZeroTime() {
        assertThat(Platform.isFxApplicationThread()).isFalse()
        val atom = TestAtom()
        runner.play(0L, atom)
        assertThat(atom.fractions).contains(0.0, 1.0)
    }

    @Test
    fun atSmallTime() {
        assertThat(Platform.isFxApplicationThread()).isFalse()
        val atom = TestAtom()
        runner.play(40L, atom)
        assertThat(atom.fractions).contains(0.0, 1.0)
        assertThat(atom.fractions.size).isGreaterThan(2)
    }
}
