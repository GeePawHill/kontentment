package org.geepawhill.contentment.fragments

import javafx.scene.Group
import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.contentment.core.Context
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ExitTest {
    private val context = Context()
    private val group = Group()

    @Test
    fun removesGroup() {
        context.canvas.children.add(group)
        val exit = Exit(group)
        exit.prepare(context)
        exit.interpolate(context, 1.0)
        assertThat(context.canvas.children.contains(group)).isFalse()
    }

    @Test
    fun isInstant() {
        context.canvas.children.add(group)
        val exit = Exit(group)
        exit.prepare(context)
        assertThat(exit.interpolate(context, .1)).isFalse()
    }

    @Test
    fun missingGroupThrows() {
        val exit = Exit(group)
        exit.prepare(context)
        Assertions.assertThrows(NullPointerException::class.java) { exit.interpolate(context, .1) }
    }
}
