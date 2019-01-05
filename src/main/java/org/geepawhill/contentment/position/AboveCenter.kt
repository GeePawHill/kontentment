package org.geepawhill.contentment.position

import org.geepawhill.contentment.core.GroupSource
import org.geepawhill.contentment.geometry.*
import org.geepawhill.contentment.utility.JfxUtility

import javafx.scene.Node

class AboveCenter @JvmOverloads constructor(private val anchor: GroupSource, private val offset: Double = 0.0) : Position {

    override fun position(node: Node, dimensions: PointPair) {
        val anchorPoint = PointPair(anchor.group().boundsInParent).north()
        JfxUtility.setTopAlignment(node)
        node.translateX = anchorPoint.x - dimensions.width() / 2.0
        node.translateY = anchorPoint.y - dimensions.height() + offset
    }

}
