package org.geepawhill.contentment.step;

import javafx.scene.Group;
import org.geepawhill.contentment.actor.Actor;
import org.geepawhill.contentment.actor.Appearance;
import org.geepawhill.contentment.actor.ScriptWorld;
import org.geepawhill.contentment.actors.Connector;
import org.geepawhill.contentment.actors.Cross;
import org.geepawhill.contentment.actors.Letters;
import org.geepawhill.contentment.actors.Marks;
import org.geepawhill.contentment.core.Gesture;
import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.fragments.Exit;
import org.geepawhill.contentment.fragments.Fader;
import org.geepawhill.contentment.fragments.Sync;
import org.geepawhill.contentment.fragments.Wipe;
import org.geepawhill.contentment.geometry.PointPair;
import org.geepawhill.contentment.player.Keyframe;
import org.geepawhill.contentment.player.Script;
import org.geepawhill.contentment.rhythm.Rhythm;
import org.geepawhill.contentment.rhythm.SimpleRhythm;
import org.geepawhill.contentment.timing.Timing;

public abstract class ScriptBuilder<SUBCLASS> {
    protected ScriptWorld world;
    protected Script script;
    protected long lastScene;
    protected long lastStall;

    public ScriptBuilder() {
        this(new SimpleRhythm());
    }

    public ScriptBuilder(Rhythm rhythm) {
        this.script = new Script(rhythm);
        this.lastScene = -1L;
        this.world = new ScriptWorld();
    }

    public abstract SUBCLASS downcast();

    public void scene(long beat) {
        if (lastScene != -1) {
            script.add(new Keyframe(lastScene, endBuild()));
        }
        lastScene = beat;
        lastStall = beat;
        buildPhrase();
        addToWorking(new Single(Timing.Companion.ms(30000), new Sync(beat * 1000)));
    }

    public void end() {
        if (lastScene == -1) throw new RuntimeException("end() called with no scene.");
        script.add(new Keyframe(lastScene, endBuild()));
        lastScene = -1;
    }

    public void sync(long beat) {
        if (lastScene == -1) throw new RuntimeException("end() called with no scene.");
        lastStall += beat;
        addToWorking(new Single(Timing.Companion.ms(30000), new Sync(lastStall * 1000)));
    }

    protected void addToWorking(Gesture step) {
        world.add(step);
    }

    public Phrase makePhrase() {
        return Phrase.phrase();
    }

    public void buildPhrase() {
        world.push(makePhrase());
    }

    public void buildChord() {
        world.push(Phrase.chord());
    }

    public void endChord() {
        world.popAndAppend();
    }

    public Phrase endBuild() {
        return world.pop();
    }

    public Appearance<? extends Actor> actor(Appearance<? extends Actor> actor) {
        return actor;
    }

    public Appearance<? extends Actor> actor(String actor) {
        return actor(world.actor(actor));
    }

    public Appearance<Letters> letters(String source) {
        return new Appearance<>(world, new Letters(world, source));
    }

    public Appearance<Marks> stroke(int fromX, int fromY, int toX, int toY) {
        return new Appearance<>(world, Marks.Companion.makeLine(world, new PointPair(fromX, fromY, toX, toY)));
    }

    public Appearance<Cross> cross(String name, double xsize, double ysize, double xoffset, double yoffset) {
        return new Appearance<>(world, new Cross(world, new Group(), actor(name).entrance(), xsize, ysize, xoffset, yoffset));
    }

    public Appearance<Marks> stroke(PointPair points) {
        return new Appearance<>(world, Marks.Companion.makeLine(world, points));
    }

    public Appearance<Marks> box(PointPair area) {
        return new Appearance<>(world, Marks.Companion.makeBox(world, area));
    }

    public Appearance<Connector> connector() {
        return new Appearance<>(world, new Connector(world, new Group()));
    }

    public SUBCLASS wipe() {
        world.add(new Single(Timing.Companion.instant(), new Wipe()));
        return downcast();
    }

    public SUBCLASS fadeOut() {
        buildChord();
        for (Appearance<? extends Actor> appearance : world.entrances()) {
            world.push(Phrase.phrase());
            world.add(new Single(Timing.Companion.ms(500d), new Fader(appearance.group(), 0)));
            world.add(new Single(Timing.Companion.instant(), new Exit(appearance.group())));
            world.popAndAppend();
        }
        world.entrances().clear();
        world.popAndAppend();
        return downcast();
    }

    public SUBCLASS assume(Format format) {
        world.assumptions().assume(format);
        return downcast();
    }

}
