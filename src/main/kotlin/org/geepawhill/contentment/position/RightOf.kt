package org.geepawhill.contentment.position

import org.geepawhill.contentment.core.GroupSource
import org.geepawhill.contentment.geometry.*
import org.geepawhill.contentment.utility.JfxUtility

import javafx.scene.Node

class RightOf @JvmOverloads constructor(private val anchor: GroupSource, private val offset: Double = 0.0) : Position {

    override fun position(node: Node, dimensions: PointPair) {
        val anchorPoint = PointPair(anchor.group().boundsInParent).east()
        JfxUtility.setTopAlignment(node)
        node.translateX = anchorPoint.x + offset
        node.translateY = anchorPoint.y - dimensions.height() / 2.0
    }

}
