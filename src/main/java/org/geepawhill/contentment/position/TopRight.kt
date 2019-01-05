package org.geepawhill.contentment.position

import org.geepawhill.contentment.geometry.*
import org.geepawhill.contentment.utility.JfxUtility

import javafx.scene.Node

class TopRight(private val anchor: Point) : Position {

    constructor(x: Double, y: Double) : this(Point(x, y)) {}

    override fun position(node: Node, dimensions: PointPair) {
        JfxUtility.setTopAlignment(node)
        node.translateX = anchor.x - dimensions.width()
        node.translateY = anchor.y
    }

}
