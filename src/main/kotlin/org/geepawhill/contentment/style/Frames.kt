package org.geepawhill.contentment.style

import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.shape.Shape
import org.geepawhill.contentment.format.Style
import org.geepawhill.contentment.format.StyleApplier

object Frames {
    val KEY = "Frame"

    fun frame(stroke: Paint, width: Double, opacity: Double): Style {
        return frame(stroke, Color.TRANSPARENT, width, opacity)
    }

    fun frame(stroke: Paint, width: Double, opacity: Double, dash: Dash): Style {
        return frame(stroke, Color.TRANSPARENT, width, opacity, dash)
    }

    @JvmOverloads
    fun frame(stroke: Paint, fill: Paint, width: Double, opacity: Double, dash: Dash = Dash.solid()): Style {
        val applier = object : StyleApplier {
            override fun apply(shape: Shape) {
                shape.stroke = stroke
                shape.fill = fill
                shape.strokeWidth = width!!
                shape.opacity = opacity!!
                shape.strokeDashArray.clear()
                shape.strokeDashArray.addAll(dash.array)
            }
        }
        val value = ("Frame: " + stroke.toString() + " Fill: " + fill.toString() + " Width: " + width + " Opacity: " + opacity
                + " Dash: " + dash)
        return Style(KEY, applier, value)

    }

    fun unspecified(): Style {
        return frame(Color.WHITE, Color.TRANSPARENT, 1.0, 1.0)
    }

}
