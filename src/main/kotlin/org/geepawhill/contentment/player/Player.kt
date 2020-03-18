package org.geepawhill.contentment.player

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.concurrent.Task
import org.geepawhill.contentment.core.Context
import org.geepawhill.contentment.core.OnFinished
import org.geepawhill.contentment.rhythm.Rhythm
import java.lang.Thread.sleep
import java.util.function.Supplier


class Player {

    private var position: Int = 0
    private val context: Context = Context()

    private val scriptProperty: SimpleObjectProperty<Script> = SimpleObjectProperty(Script())
    private val atStartProperty: SimpleBooleanProperty = SimpleBooleanProperty(true)
    private val atEndProperty: SimpleBooleanProperty = SimpleBooleanProperty(false)

    val stateProperty = SimpleObjectProperty(PlayerState.Stepping)
    val state
        get() = stateProperty.get()

    val elapsedProperty = SimpleObjectProperty(0)

    val rhythm: Rhythm
        get() = script.rhythm()

    val script: Script
        get() = scriptProperty.get()


    private val elapsedTask =
            object : Task<Unit>() {
                @Throws(Exception::class)
                protected override fun call() {
                    while (true) {
                        sleep(60000)
                        val left = elapsedProperty.value
                        if (left > 0) {
                            elapsedProperty.value = left - 1
                        }
                        if (isCancelled()) {
                            break
                        }
                    }
                }
            };


    private val isPlayOneDone: Boolean
        get() = if (position < script.size() - 1) {
            rhythm.beat() >= getSync(position + 1).target
        } else {
            rhythm.isAtEnd
        }


    init {
        this.position = 0
        val th = Thread(elapsedTask)
        th.setDaemon(true)
        th.start()
    }

    fun context(): Context {
        return context
    }

    private fun atStart(): Boolean {
        return atStartProperty.get()
    }

    private fun atEnd(): Boolean {
        return atEndProperty.get()
    }

    fun load(script: Script) {
        this.scriptProperty.set(script)
        this.setPosition(0)
        rhythm.seekHard(0.toLong())
        stateProperty.set(PlayerState.Stepping)
        context.setRhythm(script.rhythm())
    }

    private fun position(): Int {
        return position
    }

    fun forward() {
        mustBeStepping()
        if (!atEnd()) {
            nextSync().phrase.fast(context)
            setPosition(position() + 1)
            if (atEnd())
                rhythm.seekHard(Rhythm.MAX)
            else
                rhythm.seekHard(nextSync().target)
        }
    }

    private fun nextSync(): Keyframe {
        return getSync(position())
    }

    private fun getSync(sync: Int): Keyframe {
        return script[sync]
    }

    fun backward() {
        mustBeStepping()
        if (!atStart()) {
            context.wipe()
            val newPosition = position - 1
            setPosition(0)
            while (position != newPosition) {
                forward()
            }
            if (atEnd())
                rhythm.seekHard(Rhythm.MAX)
            else
                rhythm.seekHard(nextSync().target)
        }
    }

    fun playOne() {
        mustBeStepping()
        if (!atEnd()) {
            stateProperty.set(PlayerState.Playing)
            rhythm.play()
            BeatWaiter(context, nextSync().phrase,
                    Supplier<Boolean> { isPlayOneDone },
                    object : OnFinished {
                        override fun run() {
                            newPlayOneFinished()
                        }
                    }).play()
        }
    }

    private fun newPlayOneFinished() {
        rhythm.pause()
        stateProperty.set(PlayerState.Stepping)
        setPosition(position() + 1)
        if (atEnd())
            rhythm.seekHard(Rhythm.MAX)
        else
            rhythm.seekHard(nextSync().target)
    }

    private fun newPlayFinished() {
        setPosition(position() + 1)
        if (!atEnd()) {
            BeatWaiter(context, nextSync().phrase,
                    Supplier<Boolean> { isPlayOneDone },
                    object : OnFinished {
                        override fun run() {
                            newPlayFinished()
                        }
                    }).play()
        } else {
            rhythm.pause()
            stateProperty.set(PlayerState.Stepping)
        }
    }

    private fun mustBeStepping() {
        if (state !== PlayerState.Stepping) throw RuntimeException("Playing when should be Stepping.")
    }

    fun play() {
        mustBeStepping()
        if (!atEnd()) {
            stateProperty.set(PlayerState.Playing)
            rhythm.play()
            BeatWaiter(context, nextSync().phrase,
                    Supplier<Boolean> { isPlayOneDone },
                    object : OnFinished {
                        override fun run() {
                            newPlayFinished()
                        }
                    }).play()
        }
    }

    fun end() {
        mustBeStepping()
        while (position() < script.size()) {
            forward()
        }
    }

    fun start() {
        mustBeStepping()
        while (position() != 0) {
            backward()
        }
    }

    fun ultimate() {
        mustBeStepping()
        end()
        backward()
    }

    fun penultimate() {
        mustBeStepping()
        end()
        backward()
        backward()
    }

    private fun setPosition(position: Int) {
        this.position = position
        atStartProperty.set(position == 0)
        atEndProperty.set(position == script.size())
    }

    fun scriptProperty(): ObjectProperty<Script> {
        return scriptProperty
    }
}
