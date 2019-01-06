package org.geepawhill.contentment.test

import javafx.geometry.VPos
import javafx.scene.Group
import javafx.scene.text.Text
import javafx.scene.text.TextAlignment
import org.geepawhill.contentment.geometry.Point
import org.geepawhill.contentment.geometry.PointPair
import test.ContentmentAssertions.Companion.assertThat
import org.junit.jupiter.api.Test

class JavaFxTest : ContentmentTest() {

    @Test
    fun whenIsBoundsActive() {
        val group = Group()
        val text = Text("Here's some text.")
        text.textAlignment = TextAlignment.LEFT
        text.textOrigin = VPos.TOP
        text.translateX = 100.0
        text.translateY = 0.0
        println(PointPair(text))
        assertThat(PointPair(text).from).isEqualTo(Point(100.0, 0.0))
        group.children.add(text)

    }
}
