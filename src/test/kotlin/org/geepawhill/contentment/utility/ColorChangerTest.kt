package org.geepawhill.contentment.utility

import javafx.scene.paint.Color
import javafx.scene.shape.Line
import javafx.scene.text.Text
import org.assertj.core.api.Assertions.assertThat
import org.geepawhill.contentment.test.ContentmentTest
import org.junit.jupiter.api.Test

class ColorChangerTest : ContentmentTest() {
    @Test
    fun changesStrokeOnLine() {
        val line = Line(0.0, 0.0, 100.0, 100.0)
        line.stroke = Color.RED
        line.fill = Color.RED
        val changer = ColorChanger(Color.BLUE)
        changer.accept(line)
        assertThat(line.stroke).isEqualTo(Color.BLUE)
        assertThat(line.fill).isEqualTo(Color.RED)
        assertThat(changer.result).isEqualTo(Color.RED)
    }

    @Test
    fun alsoChangesFillOnText() {
        val text = Text()
        text.stroke = Color.RED
        text.fill = Color.RED
        val changer = ColorChanger(Color.BLUE)
        changer.accept(text)
        assertThat(text.stroke).isEqualTo(Color.BLUE)
        assertThat(text.fill).isEqualTo(Color.BLUE)
        assertThat(changer.result).isEqualTo(Color.RED)
    }

}
