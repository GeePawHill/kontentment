package org.geepawhill.contentment.jfx

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Percentage
import org.junit.jupiter.api.Test

class AspectTest {

    private val aspect = Aspect()

    @Test
    fun `defaults are sane`() {
        assertThat(aspect.height).isCloseTo(900.0, Percentage.withPercentage(0.01))
        assertThat(aspect.width).isCloseTo(1600.0, Percentage.withPercentage(0.01))
        assertThat(aspect.widthToHeight.toDouble()).isCloseTo(1600.0 / 900.0, Percentage.withPercentage(0.01))
    }

    @Test
    fun `widthToHeight recalculates`() {
        aspect.height = 800.0
        assertThat(aspect.widthToHeight.toDouble()).isCloseTo(2.0, Percentage.withPercentage(0.01))
        aspect.width = 800.0
        assertThat(aspect.widthToHeight.toDouble()).isCloseTo(1.0, Percentage.withPercentage(0.01))
    }
}