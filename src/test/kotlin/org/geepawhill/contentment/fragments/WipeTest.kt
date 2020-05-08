package org.geepawhill.contentment.fragments

import javafx.scene.text.Text
import org.assertj.core.api.Assertions
import org.geepawhill.contentment.core.Context
import org.junit.jupiter.api.Test

class WipeTest {
    private val context = Context()

    @Test
    fun wipes() {
        val wipe = Wipe()
        context.canvas.children.add(Text())
        wipe.prepare(context)
        Assertions.assertThat(wipe.interpolate(context, 1.0)).isFalse
        Assertions.assertThat(context.canvas.children.size).isEqualTo(0)
    }
}