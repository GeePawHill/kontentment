package org.geepawhill.contentment.jfx

import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.scene.Group
import javafx.scene.layout.Pane
import javafx.scene.media.MediaView
import javafx.scene.transform.Scale

class ScaleListener(private val host: Pane, private val child: Group, private val media: MediaView) : ChangeListener<Number> {

    override fun changed(observable: ObservableValue<out Number>, oldValue: Number, newValue: Number) {
        val newWidth = host.width
        media.fitWidth = newWidth
        val newHeight = host.height
        val scaleFactor = if (newWidth / newHeight > 16.0 / 9.0) newHeight / 900.0 else newWidth / 1600.0
        val newScale = Scale(scaleFactor, scaleFactor)
        newScale.pivotX = 0.0
        newScale.pivotY = 0.0
        child.transforms.setAll(newScale)
    }
}
