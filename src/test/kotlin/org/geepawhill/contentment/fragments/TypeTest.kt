package org.geepawhill.contentment.fragments

import javafx.scene.Group
import javafx.scene.text.Text
import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.contentment.test.ContentmentTest
import org.junit.jupiter.api.Test


class TypeTest : ContentmentTest() {
    private val owner = Group()
    private val source = "Hi mom"

    @Test
    fun addsEmptyText() {
        val mark = Type(owner, source)
        mark.prepare(context)
        assertThat(owner.children.size).isEqualTo(1)
        val added = owner.children[0]
        assertThat(added).isInstanceOf(Text::class.java)
        val text = added as Text
        assertThat(text.text).isEmpty()
    }

    @Test
    fun completedString() {
        val mark = Type(owner, source)
        mark.prepare(context)
        mark.interpolate(context, 1.0)
        val added = owner.children[0]
        val text = added as Text
        assertThat(text.text).isEqualTo(source)
    }

    @Test
    fun partialString() {
        val mark = Type(owner, source)
        mark.prepare(context)
        mark.interpolate(context, .5)
        val added = owner.children[0]
        val text = added as Text
        assertThat(text.text).isEqualTo(source.substring(0, source.length / 2))
    }
}
