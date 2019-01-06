package org.geepawhill.contentment.format

import javafx.scene.shape.Shape

@FunctionalInterface
interface StyleApplier {
    fun apply(shape: Shape)
}