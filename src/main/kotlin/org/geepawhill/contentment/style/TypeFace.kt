package org.geepawhill.contentment.style

import org.geepawhill.contentment.format.*

import javafx.scene.paint.*
import javafx.scene.shape.Shape
import javafx.scene.text.*

object TypeFace {
    val FACE = "TextFont"
    val COLOR = "TextColor"

    fun font(font: Font, width: Double?, opacity: Double?): Style {
        val applier = object : StyleApplier {
            override fun apply(shape: Shape) {
                if (shape is Text) {
                    val text = shape
                    text.font = font
                    text.strokeWidth = width!!
                    text.opacity = opacity!!
                }
            }
        }
        return Style(FACE, applier, font.family + " " + font.size)
    }

    fun smallHand(): Style {
        return font(Font("Buxton Sketch", 22.0), 1.0, 1.0)
    }

    fun mediumHand(): Style {
        return font(Font("GoodDog", 50.0), 2.0, 1.0)
    }

    fun largeHand(): Style {
        return font(Font("GoodDog", 60.0), 2.0, 1.0)
    }

    fun mediumSans(): Style {
        return font(Font("Arial", 30.0), 1.0, .8)
    }

    fun smallSans(): Style {
        return font(Font("Arial", 15.0), 1.0, .8)
    }

    fun smallFixed(): Style {
        return font(Font("Consolas", 15.0), 1.0, 1.0)
    }

    fun color(both: Paint, opacity: Double?): Style {
        return TypeFace.color(both, both, opacity)
    }

    fun color(stroke: Paint, fill: Paint, opacity: Double?): Style {
        val applier = object : StyleApplier {
            override fun apply(shape: Shape) {
                shape.stroke = stroke
                shape.fill = fill
                shape.opacity = opacity!!
            }
        }
        val value = "Stroke: " + stroke.toString() + " Fill: " + fill.toString() + " Opacity: " + opacity
        return Style(COLOR, applier, value)

    }

    fun white(): Style {
        return color(Color.WHITE, 1.0)
    }
}
