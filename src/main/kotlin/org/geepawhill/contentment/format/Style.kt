package org.geepawhill.contentment.format

import javafx.scene.shape.Shape

class Style(private val key: String, private val applier: StyleApplier, private val value: String) {

    override fun toString(): String {
        return "$key ($value)"
    }

    fun apply(shape: Shape) {
        applier.apply(shape)
    }

    fun key(): String {
        return key
    }
}
