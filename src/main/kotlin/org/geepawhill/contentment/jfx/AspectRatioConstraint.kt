package org.geepawhill.contentment.jfx

import javafx.beans.property.Property
import javafx.beans.property.ReadOnlyProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.collections.ObservableList
import javafx.scene.transform.Scale
import javafx.scene.transform.Transform

class AspectRatioConstraint(
        private val hostWidth: ReadOnlyProperty<Number>,
        private val hostHeight: ReadOnlyProperty<Number>,
        private val transforms: ObservableList<Transform>,
        private val mediaWidth: Property<Number>)
    : ChangeListener<Number> {

    init {
        hostWidth.addListener(this)
        hostHeight.addListener(this)
        changed(SimpleObjectProperty(0.0), 300, 300)
    }

    override fun changed(observable: ObservableValue<out Number>, oldValue: Number, newValue: Number) {
        val newWidth = hostWidth.value.toDouble()
        val newHeight = hostHeight.value.toDouble()
        val scaleFactor = if (newWidth / newHeight > ASPECT_RATIO) newHeight / FORCED_HEIGHT else newWidth / FORCED_WIDTH
        val newScale = Scale(scaleFactor, scaleFactor)
        newScale.pivotX = 0.0
        newScale.pivotY = 0.0
        mediaWidth.value = newWidth
        transforms.setAll(newScale)
    }

    companion object {
        private const val FORCED_WIDTH = 1600.0
        private const val FORCED_HEIGHT = 900.0
        private const val ASPECT_RATIO = FORCED_WIDTH / FORCED_HEIGHT
    }

}
