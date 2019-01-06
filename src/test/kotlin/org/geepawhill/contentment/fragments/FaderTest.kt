package org.geepawhill.contentment.fragments

import javafx.scene.Group
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.within
import org.geepawhill.contentment.core.Context
import org.junit.jupiter.api.Test

class FaderTest {
    private val group = Group()
    private val context = Context()

    @Test
    fun fadesUp() {
        group.opacity = 0.0
        val fader = Fader(group, 1.0)
        fader.prepare(context)
        fader.interpolate(context, 1.0)
        assertThat(group.opacity).isCloseTo(1.0, within(0.01))
    }

    @Test
    fun fadesDown() {
        group.opacity = 1.0
        val fader = Fader(group, 0.0)
        fader.prepare(context)
        fader.interpolate(context, 1.0)
        assertThat(group.opacity).isCloseTo(0.0, within(0.01))
    }

    @Test
    fun worksPartially() {
        group.opacity = .25
        val fader = Fader(group, 1.0)
        fader.prepare(context)
        fader.interpolate(context, .5)
        assertThat(group.opacity).isCloseTo(.625, within(0.01))
    }
}
