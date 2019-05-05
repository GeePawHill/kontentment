package org.geepawhill.contentment.fragments

import javafx.scene.Group
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import org.geepawhill.contentment.core.Context
import org.geepawhill.contentment.core.Fragment
import org.geepawhill.contentment.format.Format

/**
 * Anything loadable into a JavaFx Image.
 *
 * @author GeePaw
 */
class Snap(owner: Group, private val source: String, width: Double) : Fragment {
    private val image = Image("/org/geepawhill/scripts/idunno.jpg")
    private val imageView = ImageView()
    private var format: Format? = null

    init {
        owner.children.add(imageView)
        this.format = Format.DEFAULT
    }

    override fun prepare(context: Context) {
        interpolate(context, 0.0)
    }

    override fun interpolate(context: Context, fraction: Double): Boolean {
        imageView.image = image
        return false
    }

    fun format(format: Format) {
        this.format = format
    }
}
