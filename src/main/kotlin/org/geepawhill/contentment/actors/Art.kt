package org.geepawhill.contentment.actors

import javafx.scene.Group
import org.geepawhill.contentment.actor.Actor
import org.geepawhill.contentment.actor.ScriptWorld
import org.geepawhill.contentment.format.Format
import org.geepawhill.contentment.fragments.Entrance
import org.geepawhill.contentment.fragments.Snap
import org.geepawhill.contentment.geometry.Point
import org.geepawhill.contentment.position.Centered
import org.geepawhill.contentment.position.Position
import org.geepawhill.contentment.step.Timed
import org.geepawhill.contentment.timing.Timing

class Art(protected val world: ScriptWorld, source: String) : Actor {
    private val letters: Snap
    private var hasOval: Boolean = false
    protected val entrance: Entrance
    protected val group: Group

    init {
        this.group = Group()
        this.entrance = Entrance(group)
        this.letters = Snap(group, source, 400.0)
    }

    override fun draw(ms: Double): Art {
        val timed = Timed(ms)
        timed.add(Timing.weighted(.5), letters)
        world.add(timed)
        return this
    }

    override fun toString(): String {
        return "Image [" + letters.toString() + "]"
    }

    override fun at(position: Position) {
    }

    override fun format(format: Format) {
    }

    fun centered(x: Double, y: Double) {
        at(Centered(x, y))
    }

    fun centered(p: Point) {
        centered(p.x, p.y)
    }

    fun entrance(): Entrance {
        return entrance
    }

    override fun group(): Group {
        return group
    }

}
