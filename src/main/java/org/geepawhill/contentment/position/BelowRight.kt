package org.geepawhill.contentment.position

import org.geepawhill.contentment.core.NodeSource
import org.geepawhill.contentment.geometry.*
import org.geepawhill.contentment.utility.JfxUtility

import javafx.scene.Node

class BelowRight(private val anchor: NodeSource) : Position {


    override fun position(node: Node, dimensions: PointPair) {
        val anchorPoint = PointPair(anchor.get().boundsInParent).southeast()
        JfxUtility.setTopAlignment(node)
        node.translateX = anchorPoint.x - dimensions.width()
        node.translateY = anchorPoint.y
    }

}
