package step

import javafx.scene.Group
import org.geepawhill.contentment.actor.Actor
import org.geepawhill.contentment.actor.Appearance
import org.geepawhill.contentment.actor.ScriptWorld
import org.geepawhill.contentment.actors.Connector
import org.geepawhill.contentment.actors.Cross
import org.geepawhill.contentment.actors.Letters
import org.geepawhill.contentment.actors.Marks
import org.geepawhill.contentment.core.Gesture
import org.geepawhill.contentment.format.Format
import org.geepawhill.contentment.fragments.Exit
import org.geepawhill.contentment.fragments.Fader
import org.geepawhill.contentment.fragments.Sync
import org.geepawhill.contentment.fragments.Wipe
import org.geepawhill.contentment.geometry.Point
import org.geepawhill.contentment.geometry.PointPair
import org.geepawhill.contentment.player.Keyframe
import org.geepawhill.contentment.player.Script
import org.geepawhill.contentment.rhythm.Rhythm
import org.geepawhill.contentment.rhythm.SimpleRhythm
import org.geepawhill.contentment.timing.Timing

abstract class ScriptBuilder<SUBCLASS> @JvmOverloads constructor(rhythm: Rhythm = SimpleRhythm()) {
    protected var world: ScriptWorld
    var script: Script
    protected var lastScene: Long = 0
    protected var lastStall: Long = 0

    init {
        this.script = Script(rhythm)
        this.lastScene = -1L
        this.world = ScriptWorld()
    }

    abstract fun downcast(): SUBCLASS

    fun scene(beat: Long) {
        if (lastScene != -1L) {
            script.add(Keyframe(lastScene, endBuild()))
        }
        lastScene = beat
        lastStall = beat
        buildPhrase()
        addToWorking(Single(Timing.ms(30000.0), Sync(beat * 1000)))
    }

    fun end() {
        if (lastScene == -1L) throw RuntimeException("end() called with no scene.")
        script.add(Keyframe(lastScene, endBuild()))
        lastScene = -1
    }

    fun sync(beat: Long) {
        if (lastScene == -1L) throw RuntimeException("end() called with no scene.")
        lastStall += beat
        addToWorking(Single(Timing.ms(30000.0), Sync(lastStall * 1000)))
    }

    fun pause(beat: Long = 0) {
        scene(lastStall + beat)
    }

    protected fun addToWorking(step: Gesture) {
        world.add(step)
    }

    open fun makePhrase(): Phrase {
        return Phrase.phrase()
    }

    fun buildPhrase() {
        world.push(makePhrase())
    }

    fun buildChord() {
        world.push(Phrase.chord())
    }

    fun endChord() {
        world.popAndAppend()
    }

    fun endBuild(): Phrase {
        return world.pop()
    }

    fun actor(actor: Appearance<out Actor>): Appearance<out Actor> {
        return actor
    }

    fun actor(actor: String): Appearance<out Actor> {
        return actor(world.actor(actor))
    }

    fun ovalLetters(source: String): Appearance<Letters> {
        return Appearance(world, Letters(world, source).withOval())
    }

    fun letters(source: String): Appearance<Letters> {
        return Appearance(world, Letters(world, source))
    }

    fun oval(points: PointPair): Appearance<Marks> {
        return Appearance(world, Marks.makeBox(world, points))
    }

    fun stroke(fromX: Int, fromY: Int, toX: Int, toY: Int): Appearance<Marks> {
        return Appearance(world, Marks.makeLine(world, PointPair(fromX.toDouble(), fromY.toDouble(), toX.toDouble(), toY.toDouble())))
    }

    fun cross(name: String, xsize: Double, ysize: Double, xoffset: Double, yoffset: Double): Appearance<Cross> {
        return Appearance(world, Cross(world, Group(), actor(name).entrance(), xsize, ysize, xoffset, yoffset))
    }

    fun outline(points: List<Point>) {
        var lastPoint = points.last()
        for (to in points) {
            stroke(PointPair(lastPoint, to)).sketch()
            lastPoint = to
        }
        stroke(PointPair(lastPoint, points[0]))
    }

    fun stroke(points: PointPair): Appearance<Marks> {
        return Appearance(world, Marks.makeLine(world, points))
    }

    fun box(area: PointPair): Appearance<Marks> {
        return Appearance(world, Marks.makeBox(world, area))
    }

    fun connector(): Appearance<Connector> {
        return Appearance(world, Connector(world, Group()))
    }

    fun wipe(): SUBCLASS {
        world.add(Single(Timing.instant(), Wipe()))
        return downcast()
    }

    fun fadeOut(): SUBCLASS {
        buildChord()
        for (appearance in world.entrances()) {
            world.push(Phrase.phrase())
            world.add(Single(Timing.ms(500.0), Fader(appearance.group(), 0.0)))
            world.add(Single(Timing.instant(), Exit(appearance.group())))
            world.popAndAppend()
        }
        world.entrances().clear()
        world.popAndAppend()
        return downcast()
    }

    fun assume(format: Format): SUBCLASS {
        world.assumptions().assume(format)
        return downcast()
    }

}
