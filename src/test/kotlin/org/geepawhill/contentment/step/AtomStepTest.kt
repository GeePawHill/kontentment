package org.geepawhill.contentment.step

import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.contentment.test.ContentmentTest
import org.geepawhill.contentment.test.TestAtom
import org.geepawhill.contentment.timing.Timing
import org.junit.jupiter.api.Test
import step.Single

class AtomStepTest : ContentmentTest() {

    @Test
    fun slowTest() {
        val atom = TestAtom()
        val step = Single(Timing.ms(40.0), atom)
        runner.slow(step)
        assertThat(atom.fractions.size).isGreaterThan(2)
        assertThat(atom.fractions).contains(0.0, 1.0)
    }

    @Test
    fun fastTest() {
        val atom = TestAtom()
        val step = Single(Timing.ms(40.0), atom)
        runner.slow(step)
        assertThat(atom.fractions).contains(0.0, 1.0)
    }

}
