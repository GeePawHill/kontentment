package org.geepawhill.contentment.geometry;

import javafx.geometry.BoundingBox;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PointPairTest
{

	@Test
	public void constructors()
	{
		PointPair[] pairs = new PointPair[]
		{
				new PointPair(50d, 150d, 200d, 400d),
				new PointPair(new Point(50d, 150d), new Point(200d, 400d)),
				new PointPair(new BoundingBox(50d, 150d, 150d, 250d)),
				new PointPair(new Rectangle(50d, 150d, 150d, 250d))
		};
		for (PointPair pair : pairs)
		{
			assertEquals(50d, pair.getFrom().getX(), 0.0001);
			assertEquals(150d, pair.getFrom().getY(), 0.0001);
			assertEquals(200d, pair.getTo().getX(), 0.0001);
			assertEquals(400d, pair.getTo().getY(), 0.0001);
		}
	}

	@Test
	public void centers()
	{
		PointPair pair = new PointPair(50d, 150d, 200d, 400d);
		assertEquals(125d, pair.centerX(), 0.0001);
		assertEquals(275d, pair.centerY(), 0.0001);
		assertEquals(new Point(125d, 275d), pair.center());
	}

	@Test
	public void directions()
	{
		PointPair pair = new PointPair(50d, 150d, 200d, 400d);
		assertEquals(new Point(50d, 150d), pair.northwest());
		assertEquals(new Point(200d, 150d), pair.northeast());
		assertEquals(new Point(125d, 150d), pair.north());
		assertEquals(new Point(50d, 400d), pair.southwest());
		assertEquals(new Point(200d, 400d), pair.southeast());
		assertEquals(new Point(125d, 400d), pair.south());
		assertEquals(new Point(50d, 275d), pair.west());
		assertEquals(new Point(200d, 275d), pair.east());
	}

	@Test
	public void intersects()
	{
		PointPair base = new PointPair(100d, 100d, 200d, 200d);
		PointPair should = new PointPair(100d, 200d, 200d, 100d);
		assertEquals(new Point(150d, 150d), base.intersects(should));
	}

	@Test
	public void noIntersects()
	{
		PointPair base = new PointPair(100d, 100d, 200d, 200d);
		PointPair should = new PointPair(300d, 100d, 300d, 200d);
		assertNull(base.intersects(should));
	}

	@Test
	public void intersectsQuad()
	{
		PointPair base = new PointPair(100d, 100d, 200d, 200d);
		Point farSouthCenter = new Point(base.centerX(), 300d);
		Point center = base.center();
		Point should = base.quadIntersects(new PointPair(farSouthCenter, center));
		assertEquals(base.south(), should);
	}
}
