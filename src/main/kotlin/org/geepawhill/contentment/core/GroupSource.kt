package org.geepawhill.contentment.core

import javafx.scene.Group

interface GroupSource {
    fun group(): Group

    class NoGroupSource : RuntimeException("GroupSource has no group.")

    companion object {
        val NONE: GroupSource = object : GroupSource {
            override fun group(): Group {
                throw NoGroupSource()
            }
        }
    }

}
