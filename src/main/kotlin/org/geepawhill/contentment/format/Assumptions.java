package org.geepawhill.contentment.format;

public class Assumptions
{
	Format format = Format.DEFAULT;

	public Format format()
	{
		return format;
	}
	
	public void assume(Format format)
	{
		this.format = format;
	}

}
