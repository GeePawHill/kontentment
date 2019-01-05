package org.geepawhill.contentment.core

import java.util.function.Supplier

import javafx.scene.Node

interface NodeSource : Supplier<Node> {
    companion object {
        val NONE: NodeSource = object : NodeSource {

            override fun get(): Node {
                throw RuntimeException("No NodeSource set.")
            }
        }
    }
}
