package org.geepawhill.contentment.utility

import javafx.scene.Node
import javafx.scene.paint.Paint
import javafx.scene.shape.Shape
import javafx.scene.text.Text

class ColorChanger(private val paint: Paint) : NodeProcessor {
    var result: Paint? = null

    init {
        result = null
    }

    override fun accept(node: Node): Boolean {
        if (node is Shape) {
            val shape = node
            if (result == null) result = shape.stroke
            shape.stroke = paint
        }
        if (node is Text) {
            node.fill = paint
        }
        return true
    }
}