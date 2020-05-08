package org.geepawhill.contentment.fragments

import javafx.scene.text.Text
import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.contentment.core.Context
import org.junit.jupiter.api.Test

class WipeTest {
    private val context = Context()

    @Test
    fun wipes() {
        val wipe = Wipe()
        context.canvas.children.add(Text())
        wipe.prepare(context)
        assertThat(wipe.interpolate(context, 1.0)).isFalse()
        assertThat(context.canvas.children.size).isEqualTo(0)
    }
}