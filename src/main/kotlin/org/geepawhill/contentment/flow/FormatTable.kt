package org.geepawhill.contentment.flow

import javafx.scene.paint.Paint
import javafx.scene.text.Font
import javafx.scene.text.FontPosture
import org.geepawhill.contentment.format.Format
import org.geepawhill.contentment.style.Frames
import org.geepawhill.contentment.style.TypeFace
import org.geepawhill.contentment.utility.JfxUtility.color
import java.util.*

class FormatTable {
    val primary = color(119, 187, 65)
    val secondary = color(177, 140, 254)
    val tertiary = color(244, 194, 194)
    val emphatic = color(255, 255, 0)

    internal var sizeToColors: MutableMap<Size, Map<Color, Format>>

    internal class EntryNotFoundException(size: Size, color: Color, string: String) : RuntimeException("FormatTable.get( " + size.toString() + "," + color.toString() + " " + string) {
        companion object {

            private val serialVersionUID = -4794179798871696945L
        }
    }

    init {
        val jumbo = 80.0
        val normal = 55.0
        val small = 45.0



        sizeToColors = HashMap()

        val jumbos = HashMap<Color, Format>()
        jumbos[Color.Primary] = format(primary, jumbo)
        jumbos[Color.Secondary] = format(secondary, jumbo)
        jumbos[Color.Tertiary] = format(tertiary, jumbo)
        jumbos[Color.Emphatic] = format(emphatic, jumbo)
        sizeToColors[Size.Jumbo] = jumbos

        val normals = HashMap<Color, Format>()
        normals[Color.Primary] = format(primary, normal)
        normals[Color.Secondary] = format(secondary, normal)
        normals[Color.Tertiary] = format(tertiary, normal)
        normals[Color.Emphatic] = format(emphatic, normal)
        sizeToColors[Size.Normal] = normals

        val smalls = HashMap<Color, Format>()
        smalls[Color.Primary] = format(primary, small)
        smalls[Color.Secondary] = format(secondary, small)
        smalls[Color.Tertiary] = format(tertiary, small)
        smalls[Color.Emphatic] = format(emphatic, small)

        sizeToColors[Size.Small] = smalls
    }

    operator fun get(size: Size, color: Color): Format {
        val colorToFormat = sizeToColors[size] ?: throw EntryNotFoundException(size, color, "Size not found.")
        val result = colorToFormat[color] ?: throw EntryNotFoundException(size, color, "Color not found.")
        return result
    }

    private fun format(majorColor: Paint, fontsize: Double): Format {
        val font = Font.font("Chewed Pen BB", FontPosture.ITALIC, fontsize)
        return Format(TypeFace.font(font, 1.0, 1.0), TypeFace.color(majorColor, 1.0),
                Frames.frame(majorColor, 2.0, 1.0))
    }


}
