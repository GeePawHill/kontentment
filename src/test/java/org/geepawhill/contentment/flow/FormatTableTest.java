package org.geepawhill.contentment.flow;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FormatTableTest
{
	private FormatTable table;

	@BeforeEach
	public void before()
	{
		table = new FormatTable();
	}

	@Test
	public void findsAllNineValues()
	{
		for (Size size : Size.values())
		{
			if(size.equals(Size.None)) continue;
			for (Color color : Color.values())
			{
				if(color.equals(Color.None)) continue;
				assertThat(table.get(size, color)).isNotNull();
			}
		}
	}

	@Test
	public void missingSize()
	{
		try
		{
			table.get(Size.None, Color.Emphatic);
			Assertions.fail("No throw on missing size.");
		}
		catch (FormatTable.EntryNotFoundException e)
		{
			assertThat(e.getMessage()).endsWith("Size not found.");
		}
		try
		{
			table.get(Size.Jumbo, Color.None);
			Assertions.fail("No throw on missing color.");
		}
		catch (FormatTable.EntryNotFoundException e)
		{
			assertThat(e.getMessage()).endsWith("Color not found.");
		}

	}
}
