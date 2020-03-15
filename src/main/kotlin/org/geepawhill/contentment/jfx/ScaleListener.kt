package org.geepawhill.contentment.jfx

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.scene.Group
import javafx.scene.layout.Pane
import javafx.scene.media.MediaView
import javafx.scene.transform.Scale

class ScaleListener(private val host: Pane, private val child: Group, private val media: MediaView) : ChangeListener<Number> {

    init {
        host.widthProperty().addListener(this)
        host.heightProperty().addListener(this)
        changed(SimpleObjectProperty(0.0), 300, 300)
    }

    override fun changed(observable: ObservableValue<out Number>, oldValue: Number, newValue: Number) {
        val newWidth = host.width
        media.fitWidth = newWidth
        val newHeight = host.height
        val scaleFactor = if (newWidth / newHeight > ASPECT_RATIO) newHeight / FORCED_HEIGHT else newWidth / FORCED_WIDTH
        val newScale = Scale(scaleFactor, scaleFactor)
        newScale.pivotX = 0.0
        newScale.pivotY = 0.0
        child.transforms.setAll(newScale)
    }

    companion object {
        private const val FORCED_WIDTH = 1600.0
        private const val FORCED_HEIGHT = 900.0
        private const val ASPECT_RATIO = FORCED_WIDTH / FORCED_HEIGHT
    }

}
