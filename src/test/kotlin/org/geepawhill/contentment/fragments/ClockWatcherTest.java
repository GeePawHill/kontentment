package org.geepawhill.contentment.fragments;

import javafx.beans.property.SimpleLongProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.geepawhill.contentment.test.ContentmentAssertions.assertThat;

public class ClockWatcherTest
{

	@BeforeEach
	public void before()
	{
		
	}
	
	@Test
	public void readsProperty()
	{
		SimpleLongProperty beat = new SimpleLongProperty(100);
		assertThat(new ClockWatcher(beat).text.getText()).isEqualTo("100");
	}
	
	@Test
	public void changesWhenPropertyChanges()
	{
		SimpleLongProperty beat = new SimpleLongProperty(100);
		assertThat(new ClockWatcher(beat).text.getText()).isEqualTo("100");
		beat.set(200);
		assertThat(new ClockWatcher(beat).text.getText()).isEqualTo("200");
	}
}
