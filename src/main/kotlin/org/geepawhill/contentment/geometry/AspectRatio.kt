package org.geepawhill.contentment.geometry

import javafx.beans.property.SimpleDoubleProperty
import tornadofx.*

class AspectRatio {
    val widthToHeightProperty = SimpleDoubleProperty(16.0 / 9.0)
    var widthToHeight by widthToHeightProperty

    val hostWidthProperty = SimpleDoubleProperty(0.0)
    var hostWidth by hostWidthProperty

    val hostHeightProperty = SimpleDoubleProperty(0.0)
    var hostHeight by hostHeightProperty

    val widthProperty = SimpleDoubleProperty(0.0)
    var width by widthProperty

    val heightProperty = SimpleDoubleProperty(0.0)
    var height by heightProperty

    val xProperty = SimpleDoubleProperty(0.0)
    var x by xProperty

    val yProperty = SimpleDoubleProperty(0.0)
    var y by yProperty

    init {
        hostWidthProperty.addListener { _ -> recalculate() }
        hostHeightProperty.addListener { _ -> recalculate() }
        widthToHeightProperty.addListener { _ -> recalculate() }
    }

    private fun recalculate() {
        val impliedHeightForWidth = (1.0 / widthToHeight) * hostWidth
        if (impliedHeightForWidth < hostHeight) {
            width = hostWidth
            height = impliedHeightForWidth
        } else {
            val impliedWidthForHeight = widthToHeight * hostHeight
            width = impliedWidthForHeight
            height = hostHeight
        }
        x = (hostWidth - width) / 2.0
        y = (hostHeight - height) / 2.0
    }
}