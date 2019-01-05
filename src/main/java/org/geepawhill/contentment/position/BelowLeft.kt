package org.geepawhill.contentment.position

import org.geepawhill.contentment.core.GroupSource
import org.geepawhill.contentment.geometry.*
import org.geepawhill.contentment.utility.JfxUtility

import javafx.scene.Node

class BelowLeft(private val anchor: GroupSource) : Position {

    override fun position(node: Node, dimensions: PointPair) {
        val anchorPoint = PointPair(anchor.group().boundsInParent).southwest()
        JfxUtility.setTopAlignment(node)
        node.translateX = anchorPoint.x
        node.translateY = anchorPoint.y
    }

}
