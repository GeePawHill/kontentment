package org.geepawhill.contentment.actors;

import org.geepawhill.contentment.geometry.PointPair;

public class ArrowPoints
{
	public PointPair main;
	public PointPair toTop;
	public PointPair toBottom;
	public PointPair fromTop;
	public PointPair fromBottom;
	
	public ArrowPoints(PointPair main, PointPair top, PointPair bottom)
	{
		this.main = main;
		this.toTop = top;
		this.toBottom = bottom;
	}
	
	public ArrowPoints(PointPair main, PointPair toTop,PointPair toBottom,PointPair fromTop,PointPair fromBottom)
	{
		this.main = main;
		this.toTop = toTop;
		this.toBottom = toBottom;
		this.fromTop = fromTop;
		this.fromBottom = fromBottom;
		
	}
}
