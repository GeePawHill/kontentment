package org.geepawhill.contentment.actors

import org.geepawhill.contentment.actor.*
import org.geepawhill.contentment.format.Format
import org.geepawhill.contentment.fragments.*
import org.geepawhill.contentment.geometry.*
import org.geepawhill.contentment.position.Position
import org.geepawhill.contentment.step.Single
import org.geepawhill.contentment.timing.Timing

import javafx.scene.Group

class Marks(private val world: ScriptWorld, vararg beziers: Bezier) : Actor {
    private val marks: MutableList<Mark>
    private val entrance: Entrance
    private val group: Group = Group()

    init {
        this.entrance = Entrance(group)
        this.marks = mutableListOf<Mark>()
        for (bezier in beziers) {
            marks.add(Mark(group, BezierSource.value(bezier)))
        }
    }

    override fun format(format: Format) {
        for (mark in marks) {
            mark.format(format)
        }
    }

    override fun draw(ms: Double): Marks {
        for (mark in marks) {
            world.add(Single(Timing.ms(ms / marks.size), mark))
        }
        return this
    }

    override fun at(position: Position) {}

    fun entrance(): Entrance {
        return entrance
    }

    override fun group(): Group {
        return group
    }

    companion object {

        fun makeBox(world: ScriptWorld, points: PointPair): Marks {
            return Marks(world, jiggle(world, points.northLine()),
                    jiggle(world, points.eastLine()), jiggle(world, points.southLine()), jiggle(world, points.westLine()))
        }

        fun makeLine(world: ScriptWorld, points: PointPair): Marks {
            return Marks(world, jiggle(world, points))
        }

        private fun jiggle(world: ScriptWorld, points: PointPair): Bezier {
            val variance = points.distance() * .05
            return Bezier(points.from, world.jiggle(points.along(world.nextDouble()), 1.0, variance),
                    world.jiggle(points.along(world.nextDouble()), 1.0, variance), points.to)
        }
    }

}