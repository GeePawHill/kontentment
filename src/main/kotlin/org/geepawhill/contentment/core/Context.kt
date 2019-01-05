package org.geepawhill.contentment.core

import org.geepawhill.contentment.rhythm.Rhythm

import javafx.scene.Group

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
        return rhythm!!.beat()
    }

    fun wipe() {
        canvas.children.clear()
    }

}
