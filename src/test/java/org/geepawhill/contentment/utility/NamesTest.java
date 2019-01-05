package org.geepawhill.contentment.utility;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NamesTest
{

	@BeforeEach
	public void before()
	{
		Names.reset();
	}

	@Test
	public void sequences()
	{
		assertEquals("Test_1", Names.make("Test"));
		assertEquals("Test_2", Names.make("Test"));
	}

	@Test
	public void rootCounts()
	{
		assertEquals("Test_1", Names.make("Test"));
		assertEquals("Something_1", Names.make("Something"));
	}

	@Test
	public void className()
	{
		assertEquals("String_1", Names.make(String.class));
	}

	@Test
	public void reset()
	{
		assertEquals("String_1", Names.make(String.class));
		assertEquals("String_2", Names.make(String.class));
		Names.reset();
		assertEquals("String_1", Names.make(String.class));
	}

}
