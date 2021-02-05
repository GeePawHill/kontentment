package org.geepawhill.contentment.fragments

import javafx.scene.Group
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import org.geepawhill.contentment.core.Context
import org.geepawhill.contentment.core.Fragment
import org.geepawhill.contentment.format.Format
import org.geepawhill.contentment.geometry.PointPair
import org.geepawhill.contentment.position.Position

/**
 * Anything loadable into a JavaFx Image.
 *
 * @author GeePaw
 */
class Snap(owner: Group, private val source: String, width: Double = 0.0, height: Double = 0.0) : Fragment {
    private val image = Image(source)
    private val imageView = ImageView()
    private var format: Format? = null
    private var position: Position = Position.DEFAULT

    init {
        println("$source : ${image.width} X ${image.height} target: ${width} X $height")
        imageView.isPreserveRatio = true
        imageView.fitWidth = width
        imageView.fitHeight = height
        imageView.isSmooth = true
        owner.children.add(imageView)
        this.format = Format.DEFAULT
    }

    override fun prepare(context: Context) {
        val dimensions = PointPair(0.0, 0.0, image.width, image.height)
        println(dimensions)
        position.position(imageView, dimensions)
        interpolate(context, 0.0)
    }

    fun at(position: Position) {
        this.position = position
    }

    override fun interpolate(context: Context, fraction: Double): Boolean {
        imageView.image = image
        return false
    }

    fun format(format: Format) {
        this.format = format
    }
}
