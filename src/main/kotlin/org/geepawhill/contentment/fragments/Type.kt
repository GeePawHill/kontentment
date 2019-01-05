package org.geepawhill.contentment.fragments

import org.geepawhill.contentment.core.*
import org.geepawhill.contentment.format.Format
import org.geepawhill.contentment.geometry.PointPair
import org.geepawhill.contentment.position.Position
import org.geepawhill.contentment.style.TypeFace
import org.geepawhill.contentment.utility.JfxUtility

import javafx.scene.Group
import javafx.scene.text.Text

/**
 * Renders some type to the screen using a Text node.
 *
 * @author GeePaw
 */
class Type @JvmOverloads constructor(owner: Group, source: String?, private var format: Format = Format.DEFAULT, private var position: Position = Position.DEFAULT) : Fragment {
    val source: String = if (source == null || source.isEmpty()) " " else source
    private val text: Text = Text()
    private var lastPartial: String? = null

    init {
        owner.children.add(text)
    }

    override fun interpolate(context: Context, fraction: Double): Boolean {
        val partialSource = source.substring(0, (fraction * source.length).toInt())
        if (partialSource != lastPartial) {
            lastPartial = partialSource
            text.text = partialSource
        }
        return true
    }

    override fun prepare(context: Context) {
        text.text = source
        format.apply(TypeFace.FACE, text)
        format.apply(TypeFace.COLOR, text)
        val dimensions = PointPair(text.boundsInLocal)
        position.position(text, dimensions)
        text.text = ""
        lastPartial = ""
    }

    fun at(position: Position) {
        this.position = position
    }

    fun format(format: Format) {
        this.format = format
    }

    fun text(): Text {
        return text
    }
}
