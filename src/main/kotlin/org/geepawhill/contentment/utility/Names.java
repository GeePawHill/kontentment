package org.geepawhill.contentment.utility;

import java.util.HashMap;

public class Names
{

	private static HashMap<String, Integer> increments = new HashMap<>();

	public static <T> String make(Class<T> clazz)
	{
		return make(clazz.getSimpleName());
	}
	
	public static String make(Object object)
	{
		return make(object.getClass().getSimpleName());
	}

	public static String make(String simpleName)
	{
		Integer increment = increments.getOrDefault(simpleName, 1);
		increments.put(simpleName, increment + 1);
		return simpleName + "_" + increment;
	}

	public static void reset()
	{
		increments.clear();
	}

}
