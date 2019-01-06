package org.geepawhill.contentment.core

import org.geepawhill.contentment.utility.JfxUtility.color

import java.util.Vector

import org.geepawhill.contentment.format.Format
import org.geepawhill.contentment.geometry.*
import org.geepawhill.contentment.grid.*
import org.geepawhill.contentment.player.Script
import org.geepawhill.contentment.position.*
import org.geepawhill.contentment.rhythm.SimpleRhythm
import step.ScriptBuilder
import org.geepawhill.contentment.style.*

import javafx.scene.paint.Paint
import javafx.scene.text.*

class DemonstrationScript : ScriptBuilder<DemonstrationScript>(SimpleRhythm()) {

    private val secondaryJumbo: Format
    private val primaryJumbo: Format
    private val secondary: Paint
    private val primary: Paint
    private val emphatic: Paint

    init {

        val jumbo = 80.0
        val normal = 55.0
        val small = 45.0

        primary = color(119, 187, 65)
        secondary = color(177, 140, 254)
        emphatic = color(255, 255, 0)

        primaryJumbo = format(primary, jumbo)
        format(primary, normal)

        secondaryJumbo = format(secondary, jumbo)
        format(secondary, normal)

        format(emphatic, jumbo)
        format(emphatic, normal)
        Format(format(emphatic, small), Frames.frame(emphatic, 3.0, .7))

    }

    private fun format(majorColor: Paint, fontsize: Double): Format {
        val font = Font.font("Chewed Pen BB", FontPosture.ITALIC, fontsize)
        return Format(TypeFace.font(font, 2.0, 1.0), TypeFace.color(majorColor, 1.0),
                Frames.frame(majorColor, 5.0, 1.0))
    }

    fun make(): Script {
        leadIn()
        noob()
        end()
        return script
    }

    private fun leadIn() {
        scene(0)
        wipe()
        letters("Demonstration Script").format(primaryJumbo).at(TopLeft(XMARGIN, YMARGIN)).called("header").appear()
        assume(secondaryJumbo)
    }

    private fun noob() {
        scene(5)
        wipe()
        header("A Box With Two Strokes In A Cross")
        val grid = Grid(PointPair(50.0, 50.0, 1550.0, 850.0))
        val middleThirdLeft = grid.vertical(33)
        val middleThirdRight = grid.vertical(66)
        val middleThirdTop = grid.horizontal(33)
        val middleThirdBottom = grid.horizontal(66)

        val middleThird = grid.area(middleThirdLeft, middleThirdTop, middleThirdRight, middleThirdBottom)
        assume(primaryJumbo)
        box(middleThird).appear()
        sync(1)
        stroke(middleThird).called("middle third").appear()
        stroke(PointPair(middleThird.to.x, middleThird.from.y, middleThird.from.x, middleThird.to.y)).appear()

        cross("middle third", 100.0, 100.0, 0.0, 0.0).appear()
    }

    fun header(text: String) {
        letters(text).format(primaryJumbo).at(TopLeft(XMARGIN, YMARGIN)).called("header").sketch()
    }

    private fun headerEnd(end: String) {
        letters(end).format(secondaryJumbo).at(RightOf(actor("header").entrance())).sketch()
    }

    internal fun polygon(sides: Int, radius: Double, at: Point): Vector<Point> {
        val result = Vector<Point>()
        var i = 0
        while (i < sides) {
            val angle = i.toDouble() / sides.toDouble() * 2.0 * Math.PI
            val pointX = Math.sin(angle) * radius + at.x
            val pointY = Math.cos(angle) * radius + at.y
            result.add(Point(pointX, pointY))
            i += 1
        }
        return result
    }

    override fun downcast(): DemonstrationScript {
        return this
    }

    companion object {
        private val XMARGIN = 20.0
        private val YMARGIN = 20.0
    }
}
