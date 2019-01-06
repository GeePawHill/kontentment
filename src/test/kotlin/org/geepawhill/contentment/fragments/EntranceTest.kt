package org.geepawhill.contentment.fragments

import javafx.scene.Group
import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.contentment.core.Context
import org.junit.jupiter.api.Test

class EntranceTest {
    private val context = Context()

    @Test
    fun addsNewGroup() {
        val entrance = Entrance(Group())
        entrance.prepare(context)
        entrance.interpolate(context, 1.0)
        assertThat(context.canvas.children.contains(entrance.group())).isTrue()
    }

    @Test
    fun isInstant() {
        val entrance = Entrance(Group())
        entrance.prepare(context)
        assertThat(entrance.interpolate(context, .1)).isFalse()
    }
}
