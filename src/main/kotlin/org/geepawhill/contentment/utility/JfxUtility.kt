package org.geepawhill.contentment.utility

import javafx.embed.swing.SwingFXUtils
import javafx.geometry.VPos
import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.SnapshotParameters
import javafx.scene.layout.AnchorPane
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.text.Text
import org.geepawhill.contentment.core.GroupSource
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

object JfxUtility {
    fun anchor(node: Node) {
        AnchorPane.setTopAnchor(node, 0.0)
        AnchorPane.setBottomAnchor(node, 0.0)
        AnchorPane.setLeftAnchor(node, 0.0)
        AnchorPane.setRightAnchor(node, 0.0)
    }

    fun makeAnchorFor(node: Node): AnchorPane {
        val anchor = AnchorPane()
        anchor(node)
        anchor.children.add(node)
        return anchor
    }

    fun forEachDescendant(node: Node, processor: NodeProcessor): Boolean {
        if (!processor.accept(node)) return false
        if (node is Parent) {
            for (child in node.childrenUnmodifiable) {
                if (!forEachDescendant(child, processor)) return false
            }
        }
        return true
    }

    fun capture(node: Node) {
        val image = node.snapshot(SnapshotParameters(), null)

        var file = File("output_0.png")
        var i = 1
        while (file.exists()) file = File("output_" + i++ + ".png")
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file)
        } catch (e: IOException) {
        }

    }

    fun addIfNeeded(destination: Group, node: Node) {
        if (!destination.children.contains(node)) destination.children.add(node)
    }


    fun addIfNeeded(destination: GroupSource, node: Node) {
        addIfNeeded(destination.group(), node)
    }

    fun setTopAlignment(node: Node) {
        setVerticalAlignment(node, VPos.TOP)
    }

    fun setVerticalAlignment(node: Node, vpos: VPos) {
        if (node is Text) {
            node.textOrigin = vpos
        }
    }


    fun color(r: Int, g: Int, b: Int): Paint {
        return Color(r.toDouble() / 255.0, g.toDouble() / 255.0, b.toDouble() / 255.0, 1.0)
    }

    fun removeIfNeeded(group: Group, node: Node) {
        if (group.children.contains(node)) group.children.remove(node)

    }
}
