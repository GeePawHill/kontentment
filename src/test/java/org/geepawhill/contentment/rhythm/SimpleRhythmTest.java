package org.geepawhill.contentment.rhythm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.geepawhill.contentment.test.ContentmentTest;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleRhythmTest extends ContentmentTest
{
	final static long SHORT_TIME = 20;
	
	private SimpleRhythm rhythm;

	@BeforeEach
	public void before()
	{
		rhythm = new SimpleRhythm();
	}

	@Test
	public void newBeatIsZero()
	{
		assertThat(rhythm.beat()).isEqualTo(0L);
	}
	
	@Test
	public void seekChangesClock()
	{
		rhythm.seekHard(100L);
		assertThat(rhythm.beat()).isEqualTo(100L);
	}
	
	@Test
	public void doublePlayThrows()
	{
		assertThrows(RuntimeException.class, () -> {
			rhythm.play();
			rhythm.play();
		});
	}
	
	@Test
	public void doublePauseThrows()
	{
		assertThrows(RuntimeException.class, () -> {
		rhythm.pause();
		});
	}
	
	@Test
	public void changesBeatWhenPlayed() throws InterruptedException
	{
		rhythm.play();
		Thread.sleep(SHORT_TIME);
		assertThat(rhythm.beat()).isGreaterThanOrEqualTo(SHORT_TIME);
	}
	
	@Test
	public void pauseDoesntChangeBeat() throws InterruptedException
	{
		long atPause = rhythm.beat();
		Thread.sleep(SHORT_TIME);
		assertThat(rhythm.beat()).isEqualTo(atPause);
	}
	
	@Test
	public void pausePauses() throws InterruptedException
	{
		rhythm.play();
		Thread.sleep(SHORT_TIME);
		rhythm.pause();
		long atPause = rhythm.beat();
		Thread.sleep(SHORT_TIME);
		rhythm.update();
		assertThat(rhythm.beat()).isEqualTo(atPause);
	}
	
	@Test
	public void playAfterPauseWorks() throws InterruptedException
	{
		rhythm.play();
		Thread.sleep(SHORT_TIME);
		rhythm.pause();
		long atPause = rhythm.beat();
		rhythm.play();
		Thread.sleep(SHORT_TIME);
		assertThat(rhythm.beat()).isGreaterThanOrEqualTo(SHORT_TIME+atPause);
	}
}
