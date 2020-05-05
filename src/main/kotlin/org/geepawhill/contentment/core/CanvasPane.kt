package org.geepawhill.contentment.core

import javafx.scene.canvas.Canvas
import javafx.scene.layout.Pane

class CanvasPane : Pane() {
    val ratio = 9.0 / 16.0
    val canvas = Canvas()

    init {
        children += canvas
    }

    override fun layoutChildren() {
        val impliedHeightForWidth = ratio * width
        if (impliedHeightForWidth < height) {
            canvas.width = width
            canvas.height = impliedHeightForWidth
        } else {
            val impliedWidthForHeight = (1.0 / ratio) * height
            canvas.width = impliedWidthForHeight
            canvas.height = height
        }
        canvas.translateX = (width - canvas.width) / 2.0
        canvas.translateY = (height - canvas.height) / 2.0
    }
}