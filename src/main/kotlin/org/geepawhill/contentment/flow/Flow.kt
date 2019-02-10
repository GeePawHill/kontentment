package org.geepawhill.contentment.flow

import javafx.scene.text.Text
import org.geepawhill.contentment.actor.Appearance
import org.geepawhill.contentment.actor.ScriptWorld
import org.geepawhill.contentment.actors.Letters
import org.geepawhill.contentment.geometry.PointPair
import org.geepawhill.contentment.position.TopLeft
import org.geepawhill.contentment.style.TypeFace
import step.ChordPlayer
import step.Phrase
import java.util.*

class Flow(private val world: ScriptWorld, private val area: PointPair) {

    private val table: FormatTable
    private val lines: Vector<Line>
    private val sizer: Text

    data class Line(
            var text: String,
            var color: Color,
            var size: Size,
            var layout: PointPair? = null,
            var letters: Appearance<Letters>? = null
    )

    init {
        lines = Vector()
        sizer = Text()
        table = FormatTable()
    }

    fun blurt() {
        for (i in 0 until lines.size) {
            lines[i].letters = letters(i).appear()
        }
    }

    fun fade() {
        world.push(Phrase(ChordPlayer()))
        for (i in 0 until lines.size) {
            lines[i].letters?.fadeOut()
        }
        world.popAndAppend()
    }

    fun lines(): Vector<Line> {
        return lines
    }

    fun load(source: String) {
        lines.clear()
        val markups = source.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (markup in markups) {
            val line = Line(markup.substring(2), chooseColor(markup), chooseSize(markup))
            lines.add(line)
        }
        layout()
    }

    fun letters(i: Int): Appearance<Letters> {
        val line = lines[i]
        val position = TopLeft(line.layout!!.from)
        val result = Letters(world, line.text)
        return Appearance(world, result).format(table.get(line.size, line.color)).at(position)
    }

    private fun layout() {
        var lastEndY = area.from.y
        for (line in lines) {
            val format = table.get(line.size, line.color)
            format.apply(TypeFace.FACE, sizer)
            sizer.text = line.text
            val layout = PointPair(sizer.layoutBounds)
            val newLastEndY = lastEndY + 1.0 + layout.height()
            line.layout = PointPair(area.from.x, lastEndY + 1, layout.width(), newLastEndY)
            lastEndY = newLastEndY
        }
    }

    private fun chooseColor(substring: String): Color {
        val colorChar = substring.toLowerCase()[0]
        when (colorChar) {
            'P' -> return Color.Primary
            's' -> return Color.Secondary
            'e' -> return Color.Emphatic
            't' -> return Color.Tertiary
            else -> return Color.Primary
        }
    }

    private fun chooseSize(substring: String): Size {
        val sizeChar = substring.toLowerCase()[1]
        when (sizeChar) {
            'n' -> return Size.Normal
            'j' -> return Size.Jumbo
            's' -> return Size.Small
            else -> return Size.Normal
        }
    }

    fun size(): Int {
        return lines.size
    }

}
