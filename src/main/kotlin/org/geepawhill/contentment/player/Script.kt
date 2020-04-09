package org.geepawhill.contentment.player

import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleStringProperty
import org.geepawhill.contentment.rhythm.Rhythm
import tornadofx.*
import java.util.*

class Script(private val rhythm: Rhythm = Rhythm()) {
    private var steps: ArrayList<Keyframe> = ArrayList()
    val lengthProperty = SimpleLongProperty(0L)
    var length by lengthProperty
    val mediaProperty = SimpleStringProperty("")
    var media by mediaProperty

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