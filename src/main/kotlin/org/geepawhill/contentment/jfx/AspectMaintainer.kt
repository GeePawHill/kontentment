package org.geepawhill.contentment.jfx

import javafx.beans.property.SimpleDoubleProperty
import tornadofx.*

class AspectMaintainer(val aspect: Aspect) {
    val widthToHeightProperty = aspect.widthToHeightProperty
    val widthToHeight by widthToHeightProperty

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
        widthToHeightProperty.addListener { _, _, _ -> recalculate() }
    }

    private fun recalculate() {
        val impliedHeightForWidth = (1.0 / widthToHeight.toDouble()) * hostWidth
        if (impliedHeightForWidth < hostHeight) {
            width = hostWidth
            height = impliedHeightForWidth
        } else {
            val impliedWidthForHeight = widthToHeight.toDouble() * hostHeight
            width = impliedWidthForHeight
            height = hostHeight
        }
        x = (hostWidth - width) / 2.0
        y = (hostHeight - height) / 2.0
    }
}