package org.geepawhill.contentment.utility

import javafx.scene.Node

@FunctionalInterface
interface NodeProcessor {
    fun accept(node: Node): Boolean
}