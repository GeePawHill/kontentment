package org.geepawhill.contentment.format;

@SuppressWarnings("serial")
public class MissingFormatException extends RuntimeException
{

	public MissingFormatException(String style, String format)
	{
		super("Missing format '" + style + "' in format: '" + format + "'.");
	}

}
