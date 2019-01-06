package org.geepawhill.contentment.timing

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TimingTest {
    internal var weighted20: Timing = Timing.weighted(20.0)
    internal var wieghted80: Timing = Timing.weighted(80.0)
    internal var fixed20: Timing = Timing.ms(20.0)
    internal var fixed80: Timing = Timing.ms(80.0)
    internal var scheduler: Scheduler = Scheduler()

    @Test
    fun constructors() {
        assertWeighted(weighted20, 20.0)
        assertFixed(fixed80, 80.0)
    }

    @Test
    fun weightedThrowsOnGetIfUnset() {
        Assertions.assertThrows(RuntimeException::class.java) { weighted20.ms() }
    }

    @Test
    fun fixedThrowsIfSetTwice() {
        Assertions.assertThrows(RuntimeException::class.java) { fixed20.fix(100.0) }
    }

    @Test
    fun weightedCanFix() {
        weighted20.fix(1000.0)
        assertEquals(1000.0, weighted20.ms(), 1.0)
    }

    @Test
    fun oneWeightedEatsAllTime() {
        assertEquals(300.0, scheduler.schedule(300.0, weighted20), 0.1)
        assertEquals(300.0, weighted20.ms(), 0.1)
    }

    @Test
    fun twoWeightedEatAllTime() {
        assertEquals(300.0, scheduler.schedule(300.0, weighted20, wieghted80), 0.1)
        assertEquals(60.0, weighted20.ms(), 0.1)
        assertEquals(240.0, wieghted80.ms(), 0.1)
    }

    @Test
    fun fixedAndWeightedCoexist() {
        assertEquals(300.0, scheduler.schedule(300.0, fixed80, fixed20, weighted20, wieghted80), 0.1)
        assertEquals(40.0, weighted20.ms(), 0.1)
        assertEquals(160.0, wieghted80.ms(), 0.1)
    }

    @Test
    fun fixedWithZeroJustSums() {
        assertEquals(100.0, scheduler.schedule(0.0, fixed80, fixed20), 0.1)
    }

    @Test
    fun throwsIfRelativesButZeroTotal() {
        try {
            scheduler.schedule(0.0, weighted20, fixed80)
            fail<Any>("Did not throw on relatives but no total.")
        } catch (e: RuntimeException) {
            assertEquals(Scheduler.RELATIVES_BUT_NO_TOTAL, e.message)
        }

    }

    @Test
    fun weightedsGetMinimumTime() {
        scheduler.schedule(80.0, fixed80, weighted20)
        assertThat(weighted20.ms()).isEqualTo(.1)
    }

    private fun assertFixed(timing: Timing, expected: Double) {
        assertThat(timing.isWeighted).isFalse()
        assertThat(timing.ms()).isCloseTo(expected, within(0.1))
    }

    private fun assertWeighted(timing: Timing, weight: Double) {
        assertThat(timing.isWeighted).isTrue()
        assertThat(timing.weight()).isCloseTo(weight, within(0.1))
    }
}
