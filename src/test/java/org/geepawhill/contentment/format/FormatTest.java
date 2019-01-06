package org.geepawhill.contentment.format;


import org.geepawhill.contentment.style.Frames;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FormatTest
{

	private Format base;
	private Style style;
	private String key;

	@BeforeEach
	public void before()
	{
		style = Frames.INSTANCE.unspecified();
		base = new Format("Base");
		key = style.key();
	}

	@Test
	public void throwsOnMissingStyle()
	{

		Assertions.assertThrows(MissingFormatException.class,
				() ->  { base.require(key); });
	}

	@Test
	public void overrides()
	{
		assertThat(base.find(key)).isNull();
		base.put(style);
		assertThat(style).isEqualTo(base.find(Frames.INSTANCE.getKEY()));
	}

	@Test
	public void hasBase()
	{
		Format derived = new Format("Derived", base);
		assertThat(derived.getBase()).isEqualTo(base);
	}

	@Test
	public void findsInBase()
	{
		base.put(style);
		Format derived = new Format("Derived", base);
		assertThat(derived.find(key)).isEqualTo(style);
	}
}
