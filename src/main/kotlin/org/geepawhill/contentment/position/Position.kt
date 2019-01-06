package org.geepawhill.contentment.position

import org.geepawhill.contentment.geometry.*

import javafx.scene.Node

interface Position {

    fun position(node: Node, dimensions: PointPair)

    companion object {

        val DEFAULT: Position = Centered(ViewPort.CENTER)
    }

}