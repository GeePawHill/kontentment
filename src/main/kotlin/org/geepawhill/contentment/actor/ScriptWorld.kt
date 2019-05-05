package org.geepawhill.contentment.actor

import org.geepawhill.contentment.core.Gesture
import org.geepawhill.contentment.format.Assumptions
import org.geepawhill.contentment.geometry.Point
import org.geepawhill.contentment.step.Phrase
import java.util.*

class ScriptWorld {
    private val working: Stack<Phrase> = Stack()
    private val namedActors: HashMap<String, Appearance<out Actor>> = HashMap()
    private val actors: Vector<Appearance<out Actor>> = Vector()
    private val assumptions: Assumptions = Assumptions()
    private val random: Random = Random()

    fun add(step: Gesture) {
        getWorking().add(step)
    }

    fun jiggle(point: Point, probability: Double, variance: Double): Point {
        var newX = point.x
        var newY = point.y
        if (random.nextDouble() < probability) {
            val sign = (if (random.nextDouble() > .5) -1 else +1).toDouble()
            val change = random.nextDouble() * variance
            newX += sign * change
        }
        if (random.nextDouble() < probability) {
            val sign = (if (random.nextDouble() > .5) -1 else +1).toDouble()
            val change = random.nextDouble() * variance
            newY += sign * change
        }
        return Point(newX, newY)
    }


    fun flip(truePercentage: Double): Boolean {
        return random.nextDouble() < truePercentage / 100.0
    }

    fun nextDouble(): Double {
        return random.nextDouble()
    }

    fun push(phrase: Phrase) {
        working.push(phrase)
    }

    fun pop(): Phrase {
        return working.pop()
    }

    fun popAndAppend() {
        val popped = pop()
        add(popped)
    }

    fun actor(actor: String): Appearance<out Actor> {
        if (!namedActors.containsKey(actor)) throw RuntimeException("Can't find actor: [$actor]")
        return namedActors[actor]!!
    }

    fun callActor(name: String, actor: Appearance<out Actor>) {
        namedActors[name] = actor
    }

    private fun getWorking(): Phrase {
        return working.peek()
    }

    fun assumptions(): Assumptions {
        return assumptions
    }

    fun addActor(appearance: Appearance<out Actor>) {
        actors.add(appearance)
    }

    fun removeActor(appearance: Appearance<out Actor>) {
        actors.remove(appearance)
    }

    fun entrances(): Vector<Appearance<out Actor>> {
        return actors
    }
}
