package org.geepawhill.contentment.actors

import javafx.scene.Group
import org.geepawhill.contentment.actor.Actor
import org.geepawhill.contentment.actor.ScriptWorld
import org.geepawhill.contentment.format.Format
import org.geepawhill.contentment.fragments.Entrance
import org.geepawhill.contentment.fragments.Mark
import org.geepawhill.contentment.fragments.Type
import org.geepawhill.contentment.geometry.*
import org.geepawhill.contentment.position.Centered
import org.geepawhill.contentment.position.Position
import org.geepawhill.contentment.step.Timed
import org.geepawhill.contentment.timing.Timing

class Letters(protected val world: ScriptWorld, source: String) : Actor {
    private val letters: Type
    private var hasOval: Boolean = false

    private val controlJiggler: Jiggler
    private val northJiggler: Jiggler
    private val east: Mark
    private val west: Mark
    private var eastHalfBezier: Bezier? = null
    private var westHalfBezier: Bezier? = null
    protected val entrance: Entrance
    protected val group: Group

    internal inner class EastGetter : BezierSource {

        override fun get(): Bezier {
            return eastHalfPoints()
        }
    }

    internal inner class WestGetter : BezierSource {

        override fun get(): Bezier {
            return westHalfPoints()
        }
    }

    init {
        this.group = Group()
        this.entrance = Entrance(group)
        this.northJiggler = Jiggler(.5, 6.0)
        this.controlJiggler = Jiggler(.4, 30.0)

        this.letters = Type(group, source, Format.DEFAULT, Position.DEFAULT)
        this.east = Mark(group, EastGetter())
        this.west = Mark(group, WestGetter())
    }

    fun withOval(): Letters {
        hasOval = true
        return this
    }

    override fun draw(ms: Double): Letters {
        var localms = ms
        if (ms != 0.0 && ms != 1.0) localms = letters.source.length * 25.0
        val timed = Timed(localms)
        timed.add(Timing.weighted(7.0), letters)
        if (hasOval) {
            timed.add(Timing.weighted(1.5), east)
            timed.add(Timing.weighted(1.5), west)
        }
        world.add(timed)
        return this
    }

    override fun toString(): String {
        return "Letters [" + letters.toString() + "]"
    }

    private fun setPointsIfNeeded() {
        if (eastHalfBezier == null) {
            val raw = PointPair(letters.text())
            val points = raw.grow(raw.width() * 0.25, raw.height() * 0.15)
            eastHalfBezier = Bezier(points.north(), controlJiggler.jiggle(points.northeast()),
                    controlJiggler.jiggle(points.southeast()), points.south())
            westHalfBezier = Bezier(points.south(), controlJiggler.jiggle(points.southwest()),
                    controlJiggler.jiggle(points.northwest()), northJiggler.jiggle(points.north()))
        }
    }

    private fun eastHalfPoints(): Bezier {
        setPointsIfNeeded()
        return eastHalfBezier!!
    }

    fun westHalfPoints(): Bezier {
        setPointsIfNeeded()
        return westHalfBezier!!
    }

    override fun at(position: Position) {
        letters.at(position)
    }

    override fun format(format: Format) {
        letters.format(format)
        east.format(format)
        west.format(format)
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
