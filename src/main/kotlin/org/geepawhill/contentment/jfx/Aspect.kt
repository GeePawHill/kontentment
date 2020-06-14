package org.geepawhill.contentment.jfx

import javafx.beans.binding.Bindings
import javafx.beans.property.SimpleDoubleProperty
import tornadofx.*

class Aspect {

    val widthProperty = SimpleDoubleProperty(DEFAULT_WIDTH)
    var width by widthProperty

    val heightProperty = SimpleDoubleProperty(DEFAULT_HEIGHT)
    var height by heightProperty

    val widthToHeightProperty = Bindings.divide(widthProperty, heightProperty)
    val widthToHeight by widthToHeightProperty

    companion object {
        const val DEFAULT_WIDTH = 1600.0
        const val DEFAULT_HEIGHT = 900.0
    }
}