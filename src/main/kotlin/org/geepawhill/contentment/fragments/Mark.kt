package org.geepawhill.contentment.fragments

import org.geepawhill.contentment.core.*
import org.geepawhill.contentment.format.Format
import org.geepawhill.contentment.geometry.BezierSource
import org.geepawhill.contentment.style.Frames

import javafx.scene.Group
import javafx.scene.shape.Path

/**
 * Animates a single (bezier) line in a given format to the screen using a Path
 * node.
 *
 * @author GeePaw
 */
class Mark(owner: Group, private val source: BezierSource) : Fragment {
    private val path: Path = Path()
    private var format: Format? = null

    init {
        owner.children.add(path)
        this.format = Format.DEFAULT
    }

    override fun prepare(context: Context) {
        format!!.apply(Frames.KEY, path)
        interpolate(context, 0.0)
    }

    override fun interpolate(context: Context, fraction: Double): Boolean {
        source.get().splitToPath(fraction, path)
        return true
    }

    fun format(format: Format) {
        this.format = format
    }
}
