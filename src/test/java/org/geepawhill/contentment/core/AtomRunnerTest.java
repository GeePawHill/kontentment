package org.geepawhill.contentment.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.test.*;

import javafx.application.Platform;
import org.junit.jupiter.api.Test;

public class AtomRunnerTest extends ContentmentTest
{
	@Test
	public void atZeroTime()
	{
		assertThat(Platform.isFxApplicationThread()).isFalse();
		TestAtom atom = new TestAtom();
		runner.play(0L,atom);
		assertThat(atom.fractions).contains(0d,1d);
	}
	
	@Test
	public void atSmallTime()
	{
		assertThat(Platform.isFxApplicationThread()).isFalse();
		TestAtom atom = new TestAtom();
		runner.play(40L,atom);
		assertThat(atom.fractions).contains(0d,1d);
		assertThat(atom.fractions.size()).isGreaterThan(2);
	}
}
