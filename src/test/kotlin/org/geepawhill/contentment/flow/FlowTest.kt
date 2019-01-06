package org.geepawhill.contentment.flow

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Percentage.withPercentage
import org.geepawhill.contentment.actor.ScriptWorld
import org.geepawhill.contentment.geometry.PointPair
import org.geepawhill.contentment.test.ContentmentTest
import org.junit.jupiter.api.Test
import java.util.*

class FlowTest : ContentmentTest() {

    private val AREA = PointPair(50.0, 90.0, 450.0, 590.0)

    private val world = ScriptWorld()
    private val flow = Flow(world, AREA)

    @Test
    fun linesHaveCorrectString() {
        flow.load("pjThis is primary jumbo.\n"
                + "snThis is secondary normal.\n"
                + "xnThis is primary normal.\n"
                + "esThis is emphatic small.\n")
        assertThat(flow.lines().size).isEqualTo(4)
        assertLine(0, "This is primary jumbo.", Color.Primary, Size.Jumbo)
        assertLine(1, "This is secondary normal.", Color.Secondary, Size.Normal)
        assertLine(2, "This is primary normal.", Color.Primary, Size.Normal)
        assertLine(3, "This is emphatic small.", Color.Emphatic, Size.Small)
        assertCoordinates(flow.lines())
    }

    @Test
    fun loadClearsOld() {
        flow.load("pjABCDE\n")
        assertThat(flow.lines().size).isEqualTo(1)
        flow.load("pjABCDE\npjXYZZY")
        assertThat(flow.lines().size).isEqualTo(2)
    }

    fun assertLine(index: Int, text: String, color: Color, size: Size) {
        val (text1, color1, size1) = flow.lines()[index]
        assertThat(text1).isEqualTo(text)
        assertThat(color1).isEqualTo(color)
        assertThat(size1).isEqualTo(size)
    }

    fun assertCoordinates(lines: Vector<Flow.Line>) {
        var lastEndY: Double? = AREA.from.y - 1
        for ((_, _, _, layout) in lines) {
            assertThat(layout!!.from.x).isCloseTo(AREA.from.x, withPercentage(.5))
            assertThat(layout.from.y).isGreaterThan(lastEndY)
            lastEndY = layout.to.y
        }
    }

}
