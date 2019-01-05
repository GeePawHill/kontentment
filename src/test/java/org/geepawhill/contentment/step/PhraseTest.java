package org.geepawhill.contentment.step;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.core.OnFinished;
import org.geepawhill.contentment.test.ContentmentTest;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * PhraseTest tests that phrases can play their gestures fast (synchronously) or
 * slow (asynchronously).
 * 
 * @author GeePaw
 *
 */
public class PhraseTest extends ContentmentTest
{
	private Phrase onlyOne;
	private Phrase both;
	private TestGesture one;
	private TestGesture two;
	private boolean gotFinish;
	private OnFinished recordFinish;

	@BeforeEach
	public void before()
	{
		one = new TestGesture();
		two = new TestGesture();

		onlyOne = Phrase.phrase();
		onlyOne.add(one);

		both = Phrase.phrase();
		both.add(one);
		both.add(two);

		gotFinish = false;

		recordFinish = () -> gotFinish = true;
	}

	@Test
	public void fastPlaysOne()
	{
		onlyOne.fast(getContext());
		assertPlayed(one);
	}

	@Test
	public void fastPlaysTwo()
	{
		both.fast(getContext());
		assertPlayed(one);
		assertPlayed(two);
	}

	@Test
	public void slowPlaysOne()
	{
		onlyOne.slow(getContext(), recordFinish);
		assertPlaying(one);
		one.finish(getContext());
		assertPlayed(one);
		assertThat(gotFinish).isTrue();
	}

	@Test
	public void slowPlaysBoth()
	{
		both.slow(getContext(), recordFinish);
		assertPlaying(one);
		assertUndone(two);
		one.finish(getContext());
		assertPlayed(one);
		assertPlaying(two);
		two.finish(getContext());
		assertPlayed(two);
		assertThat(gotFinish).isTrue();
	}

	private void assertPlayed(TestGesture Step)
	{
		assertThat(Step.state).isEqualTo(TestGesture.State.Played);
	}

	private void assertUndone(TestGesture Step)
	{
		assertThat(Step.state).isEqualTo(TestGesture.State.Undone);
	}

	private void assertPlaying(TestGesture Step)
	{
		assertThat(Step.state).isEqualTo(TestGesture.State.Playing);
	}
}
