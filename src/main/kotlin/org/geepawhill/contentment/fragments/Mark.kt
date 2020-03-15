package org.geepawhill.contentment.fragments

import javafx.scene.Group
import javafx.scene.shape.Path
import org.geepawhill.contentment.core.Context
import org.geepawhill.contentment.core.Fragment
import org.geepawhill.contentment.format.Format
import org.geepawhill.contentment.geometry.Bezier
import org.geepawhill.contentment.style.Frames

/**
 * Animates a single (bezier) line in a given format to the screen using a Path
 * node.
 *
 * @author GeePaw
 */
class Mark(owner: Group, private val source: () -> Bezier) : Fragment {

    private val path: Path = Path()
    private var format: Format = Format.DEFAULT

    init {
        owner.children.add(path)
    }

    override fun prepare(context: Context) {
        format.apply(Frames.KEY, path)
        interpolate(context, 0.0)
    }

    override fun interpolate(context: Context, fraction: Double): Boolean {
        source().splitToPath(fraction, path)
        return true
    }

    fun format(format: Format) {
        this.format = format
    }
}
