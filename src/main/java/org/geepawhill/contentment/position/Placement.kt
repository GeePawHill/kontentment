package org.geepawhill.contentment.position

import org.geepawhill.contentment.core.NodeSource
import org.geepawhill.contentment.geometry.PointPair
import org.geepawhill.contentment.utility.JfxUtility

import javafx.geometry.*
import javafx.scene.Node

class Placement(private val area: PointPair, private val above: NodeSource, private val horizontal: HPos) : Position {

    override fun position(node: Node, dimensions: PointPair) {
        var remainder = area
        if (above !== NodeSource.NONE) {
            val newY = PointPair(above.get().boundsInParent).south().y
            remainder = PointPair(remainder.from.x, newY, remainder.to.x, remainder.to.y)
        }
        when (horizontal) {
            HPos.LEFT -> node.translateX = remainder.west().x
            HPos.RIGHT -> node.translateX = remainder.east().x - dimensions.width()
            HPos.CENTER -> node.translateX = remainder.centerX() - dimensions.width() / 2
        }

        node.translateY = remainder.north().y
        JfxUtility.setVerticalAlignment(node, VPos.TOP)
    }
}
