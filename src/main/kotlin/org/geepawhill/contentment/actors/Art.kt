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

class Art(protected val world: ScriptWorld, source: String, width: Double = 0.0, height: Double = 0.0) : Actor {
    private val snap: Snap
    protected val entrance: Entrance
    protected val group: Group

    init {
        this.group = Group()
        this.entrance = Entrance(group)
        this.snap = Snap(group, source, width, height)
    }

    override fun draw(ms: Double): Art {
        val timed = Timed(ms)
        timed.add(Timing.weighted(.5), snap)
        world.add(timed)
        return this
    }

    override fun toString(): String {
        return "Image [" + snap.toString() + "]"
    }

    override fun at(position: Position) {
        snap.at(position)
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
