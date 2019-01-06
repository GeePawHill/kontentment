package org.geepawhill.contentment.player

import javafx.beans.property.BooleanProperty
import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import org.geepawhill.contentment.core.Context
import org.geepawhill.contentment.core.OnFinished
import org.geepawhill.contentment.rhythm.Rhythm

import java.util.function.Supplier


class Player {
    private var position: Int = 0
    private val context: Context

    private val scriptProperty: SimpleObjectProperty<Script>
    private val stateProperty: SimpleObjectProperty<PlayerState>
    private val atStartProperty: SimpleBooleanProperty
    private val atEndProperty: SimpleBooleanProperty

    val state: PlayerState
        get() = stateProperty.get()

    val isPlayOneDone: Boolean
        get() = if (position < script.size() - 1) {
            if (rhythm.beat() >= getSync(position + 1).target)
                true
            else
                false
        } else {
            rhythm.isAtEnd
        }

    val rhythm: Rhythm
        get() = script.rhythm()

    val script: Script
        get() = scriptProperty.get()

    init {
        this.atStartProperty = SimpleBooleanProperty(true)
        this.atEndProperty = SimpleBooleanProperty(false)
        this.stateProperty = SimpleObjectProperty(PlayerState.Stepping)
        this.scriptProperty = SimpleObjectProperty(Script())
        this.context = Context()
        this.position = 0
    }

    fun context(): Context {
        return context
    }

    fun atStartProperty(): BooleanProperty {
        return atStartProperty
    }

    fun atEndProperty(): BooleanProperty {
        return atEndProperty
    }

    fun atStart(): Boolean {
        return atStartProperty.get()
    }

    fun atEnd(): Boolean {
        return atEndProperty.get()
    }

    fun load(script: Script) {
        this.scriptProperty.set(script)
        this.setPosition(0)
        rhythm.seekHard(0.toLong())
        stateProperty.set(PlayerState.Stepping)
        context.setRhythm(script.rhythm())
    }

    fun position(): Int {
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
        //		mustBeStepping();
        //		if (!atStart())
        //		{
        //			setPosition(position() - 1);
        //			nextSync().phrase.undo(context);
        //			getRhythm().seekHard(nextSync().target);
        //		}
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

    fun newPlayOneFinished() {
        rhythm.pause()
        stateProperty.set(PlayerState.Stepping)
        setPosition(position() + 1)
        if (atEnd())
            rhythm.seekHard(Rhythm.MAX)
        else
            rhythm.seekHard(nextSync().target)
    }

    fun newPlayFinished() {
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

    fun setPosition(position: Int) {
        this.position = position
        atStartProperty.set(position == 0)
        atEndProperty.set(position == script.size())
    }


    fun stateProperty(): ObjectProperty<PlayerState> {
        return stateProperty
    }

    fun scriptProperty(): ObjectProperty<Script> {
        return scriptProperty
    }
}
