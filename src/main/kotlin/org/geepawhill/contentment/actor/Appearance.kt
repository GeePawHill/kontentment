package org.geepawhill.contentment.actor

import javafx.scene.Group
import org.geepawhill.contentment.core.GroupSource
import org.geepawhill.contentment.format.Format
import org.geepawhill.contentment.fragments.Entrance
import org.geepawhill.contentment.fragments.Exit
import org.geepawhill.contentment.fragments.Fader
import org.geepawhill.contentment.geometry.Point
import org.geepawhill.contentment.position.Centered
import org.geepawhill.contentment.position.Position
import org.geepawhill.contentment.timing.Timing
import step.Phrase
import step.Single

class Appearance<ACTOR : Actor>(private val world: ScriptWorld, val actor: ACTOR) : GroupSource {
    private val entrance: Entrance = Entrance(actor.group())

    init {
        actor.format(world.assumptions().format())
    }

    fun entrance(): Entrance {
        return entrance
    }

    @JvmOverloads
    fun sketch(time: Double = 500.0): Appearance<ACTOR> {
        world.add(Single(Timing.instant(), entrance))
        world.addActor(this)
        actor.draw(time)
        return this
    }

    fun appear(): Appearance<ACTOR> {
        world.add(Single(Timing.instant(), entrance))
        world.addActor(this)
        actor.draw(1.0)
        return this
    }

    fun disappear(): Appearance<ACTOR> {
        world.removeActor(this)
        world.add(Single(Timing.instant(), Exit(entrance.group())))
        return this
    }

    fun called(name: String): Appearance<ACTOR> {
        world.callActor(name, this)
        return this
    }

    fun fadeDown(): Appearance<ACTOR> {
        world.add(Single(Timing.ms(500.0), Fader(actor.group(), 0.0)))
        return this
    }

    fun fadeOut(): Appearance<ACTOR> {
        world.removeActor(this)
        world.push(Phrase.phrase())
        world.add(Single(Timing.ms(500.0), Fader(actor.group(), 0.0)))
        world.add(Single(Timing.instant(), Exit(entrance.group())))
        world.popAndAppend()
        return this
    }

    fun fadeIn(ms: Double = 500.0): Appearance<ACTOR> {
        world.addActor(this)
        world.push(Phrase.phrase())
        world.add(Single(Timing.instant(), entrance))
        world.add(Single(Timing.instant(), Fader(actor.group(), 0.0)))
        actor.draw(1.0)
        world.add(Single(Timing.ms(ms), Fader(actor.group(), 1.0)))
        world.popAndAppend()
        return this
    }

    fun fadeUp(): Appearance<ACTOR> {
        world.add(Single(Timing.ms(500.0), Fader(actor.group(), 1.0)))
        return this
    }

    fun format(format: Format): Appearance<ACTOR> {
        actor.format(format)
        return this
    }

    fun at(position: Position): Appearance<ACTOR> {
        actor.at(position)
        return this
    }

    fun centered(point: Point): Appearance<ACTOR> {
        at(Centered(point))
        return this
    }

    fun centered(x: Int, y: Int): Appearance<ACTOR> {
        at(Centered(x.toDouble(), y.toDouble()))
        return this
    }

    override fun group(): Group {
        return entrance.group()
    }

}
