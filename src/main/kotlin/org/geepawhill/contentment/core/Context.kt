package org.geepawhill.contentment.core

import javafx.scene.Group
import org.geepawhill.contentment.rhythm.Rhythm

class Context {
    val canvas: Group = Group()

    private var rhythm: Rhythm? = null

    fun rhythm(): Rhythm? {
        return rhythm
    }

    fun setRhythm(rhythm: Rhythm) {
        this.rhythm = rhythm
    }

    fun beat(): Long {
        return rhythm!!.beat
    }

    fun wipe() {
        canvas.children.clear()
    }

}
