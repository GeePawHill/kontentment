package org.geepawhill.contentment.geometry

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Percentage
import org.geepawhill.contentment.jfx.AspectMaintainer
import org.junit.jupiter.api.Test

class AspectMaintainerTest {

    private val ratio = AspectMaintainer()

    @Test
    fun `defaults to 16 x 9`() {
        assertThat(ratio.widthToHeight).isCloseTo(16.0 / 9.0, Percentage.withPercentage(0.01))
    }

    @Test
    fun `exact dimension match`() {
        ratio.hostWidth = 1600.0
        ratio.hostHeight = 900.0
        assertThat(ratio.width).isCloseTo(1600.0, Percentage.withPercentage(0.01))
        assertThat(ratio.height).isCloseTo(900.0, Percentage.withPercentage(0.01))
    }

    @Test
    fun `smaller dimension match`() {
        ratio.hostWidth = 1280.0
        ratio.hostHeight = 720.0
        assertThat(ratio.width).isCloseTo(1280.0, Percentage.withPercentage(0.01))
        assertThat(ratio.height).isCloseTo(720.0, Percentage.withPercentage(0.01))
    }

    @Test
    fun `larger dimension match`() {
        ratio.hostWidth = 1920.0
        ratio.hostHeight = 1080.0
        assertThat(ratio.width).isCloseTo(1920.0, Percentage.withPercentage(0.01))
        assertThat(ratio.height).isCloseTo(1080.0, Percentage.withPercentage(0.01))
    }

    @Test
    fun `not enough width`() {
        ratio.hostWidth = 1600.0
        ratio.hostHeight = 1080.0
        assertThat(ratio.width).isCloseTo(1600.0, Percentage.withPercentage(0.01))
        assertThat(ratio.height).isCloseTo(900.0, Percentage.withPercentage(0.01))
        assertThat(ratio.y).isCloseTo(90.0, Percentage.withPercentage(0.01))
        assertThat(ratio.x).isCloseTo(0.0, Percentage.withPercentage(0.01))
    }

    @Test
    fun `not enough height`() {
        ratio.hostWidth = 1920.0
        ratio.hostHeight = 720.0
        assertThat(ratio.width).isCloseTo(1280.0, Percentage.withPercentage(0.01))
        assertThat(ratio.height).isCloseTo(720.0, Percentage.withPercentage(0.01))
        assertThat(ratio.y).isCloseTo(0.0, Percentage.withPercentage(0.01))
        assertThat(ratio.x).isCloseTo(320.0, Percentage.withPercentage(0.01))
    }

    @Test
    fun `ratio change`() {
        ratio.hostWidth = 1600.0
        ratio.hostHeight = 1200.0
        assertThat(ratio.height).isCloseTo(900.0, Percentage.withPercentage(0.01))
        ratio.widthToHeight = 4.0 / 3.0
        assertThat(ratio.width).isCloseTo(1600.0, Percentage.withPercentage(0.01))
        assertThat(ratio.height).isCloseTo(1200.0, Percentage.withPercentage(0.01))
    }

}