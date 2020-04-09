package org.geepawhill.contentment.player

import javafx.beans.property.SimpleLongProperty
import javafx.scene.media.MediaPlayer
import org.geepawhill.contentment.rhythm.Rhythm
import org.geepawhill.contentment.rhythm.SimpleRhythm
import tornadofx.*
import java.util.*

class Script(private val rhythm: Rhythm = SimpleRhythm()) {
    internal var steps: ArrayList<Keyframe> = ArrayList()
    val lengthProperty = SimpleLongProperty(0L)
    var length by lengthProperty
    val mediaPlayer: MediaPlayer?
        get() = rhythm.mediaPlayer

    fun size(): Int {
        return steps.size
    }

    operator fun get(index: Int): Keyframe {
        return steps[index]
    }

    fun add(step: Keyframe): Script {
        steps.add(step)
        return this
    }

    fun rhythm(): Rhythm {
        return rhythm
    }
}