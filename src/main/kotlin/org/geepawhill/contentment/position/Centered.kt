package org.geepawhill.contentment.position

import javafx.scene.Node
import org.geepawhill.contentment.core.GroupSource
import org.geepawhill.contentment.geometry.Point
import org.geepawhill.contentment.geometry.PointPair
import org.geepawhill.contentment.utility.JfxUtility

class Centered @JvmOverloads constructor(private val actor: GroupSource?, private var anchor: Point? = null) : Position {

    constructor(anchor: Point) : this(null, anchor) {}

    constructor(x: Double, y: Double) : this(Point(x, y)) {}

    override fun position(node: Node, dimensions: PointPair) {
        JfxUtility.setTopAlignment(node)
        if (actor != null) anchor = PointPair(actor.group().boundsInParent).center()
        node.translateX = anchor!!.x - dimensions.width() / 2.0
        node.translateY = anchor!!.y - dimensions.height() / 2.0
    }

}
