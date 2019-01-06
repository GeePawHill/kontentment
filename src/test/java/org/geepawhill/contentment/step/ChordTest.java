package org.geepawhill.contentment.step;

import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.OnFinished;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import step.Phrase;

import static org.assertj.core.api.Assertions.assertThat;

public class ChordTest {
    private Phrase onlyOne;
    private Phrase both;
    private Context context;
    private TestGesture one;
    private TestGesture two;
    private boolean gotFinish;
    private OnFinished recordFinish;

    @BeforeEach
    public void before() {

        context = new Context();
        one = new TestGesture();
        two = new TestGesture();

        onlyOne = Phrase.Companion.chord();
        onlyOne.add(one);

        both = Phrase.Companion.chord();
        both.add(one);
        both.add(two);

        gotFinish = false;

        recordFinish = () -> gotFinish = true;
    }

    @Test
    public void fastOne() {
        onlyOne.fast(context);
        assertPlayed(one);
    }

    @Test
    public void fastBoth() {
        both.fast(context);
        assertPlayed(one);
        assertPlayed(two);
    }

    @Test
    public void slowOne() {
        onlyOne.slow(context, recordFinish);
        assertPlaying(one);
        one.finish(context);
        assertPlayed(one);
        assertThat(gotFinish).isTrue();
    }

    @Test
    public void slowBoth() {
        both.slow(context, recordFinish);
        assertPlaying(one);
        assertPlaying(two);
        one.finish(context);
        assertThat(gotFinish).isFalse();
        two.finish(context);
        assertPlayed(one);
        assertPlayed(two);
        assertThat(gotFinish).isTrue();
    }

    private void assertPlayed(TestGesture Step) {
        assertThat(Step.state).isEqualTo(TestGesture.State.Played);
    }

    private void assertPlaying(TestGesture Step) {
        assertThat(Step.state).isEqualTo(TestGesture.State.Playing);
    }
}
