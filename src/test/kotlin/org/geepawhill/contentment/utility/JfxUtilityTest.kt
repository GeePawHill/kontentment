package org.geepawhill.contentment.utility

import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.text.Text
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class JfxUtilityTest {

    private var calls: ArrayList<Node> = ArrayList()
    private val root: Group = Group()
    private val parentWithChildren: Group = Group()
    private val parentWithoutChildren: Group = Group()
    private val leafOffRoot: Node = Text()
    private val leaf2: Node = Text()
    private val leaf3: Node = Text()

    init {
        root.children.addAll(parentWithChildren, parentWithoutChildren, leafOffRoot)
        parentWithChildren.children.addAll(leaf2, leaf3)
    }

    private fun process(node: Node): Boolean {
        calls.add(node)
        return true
    }

    class ProcessRecorder(val todo: (node: Node) -> Boolean) : NodeProcessor {
        override fun accept(node: Node): Boolean {
            return todo(node)
        }

    }

    @Test
    fun descendant() {
        JfxUtility.forEachDescendant(root, ProcessRecorder { node -> process(node) })
        assertThat(calls).containsExactly(root, parentWithChildren, leaf2, leaf3, parentWithoutChildren, leafOffRoot)
    }

}
