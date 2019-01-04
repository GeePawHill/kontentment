package org.geepawhill.contentment.geometry;

import javafx.scene.shape.*;

public class Bezier
{
	public final Point start;
	public final Point handle1;
	public final Point handle2;
	public final Point end;

	public Bezier(PointPair points)
	{
		this(points.from, points.to);
	}

	public Bezier(Point start, Point end)
	{
		PointPair pair = new PointPair(start, end);
		this.handle1 = pair.along(1d / 3d);
		this.handle2 = pair.along(2d / 3d);
		this.start = start;
		this.end = end;
	}

	public Bezier(Point start, Point handle1, Point handle2, Point end)
	{
		this.start = start;
		this.handle1 = handle1;
		this.handle2 = handle2;
		this.end = end;
	}

	public Point along(double fraction)
	{
		// A = -K0 + 3K1 + -3K2 + K3
		// B = 3K0 + -6K1 + 3K2
		// C = -3K0 + 3K1
		// D = K0

		double ax = -start.x + 3d * handle1.x - 3d * handle2.x + end.x;
		double ay = -start.y + 3d * handle1.y - 3d * handle2.y + end.y;
		double bx = 3d * start.x - 6d * handle1.x + 3d * handle2.x;
		double by = 3d * start.y - 6d * handle1.y + 3d * handle2.y;
		double cx = -3d * start.x + 3d * handle1.x;
		double cy = -3d * start.y + 3d * handle1.y;
		double dx = start.x;
		double dy = start.y;

		double x = ax * (fraction * fraction * fraction) + bx * (fraction * fraction) + cx * fraction + dx;
		double y = ay * (fraction * fraction * fraction) + by * (fraction * fraction) + cy * fraction + dy;
		return new Point(x, y);
	}
	
	public void splitToPath(double fraction, Path path)
	{
		path.getElements().clear();
		if(fraction==0) return;
		Bezier before = split(fraction);
		path.getElements().add(new MoveTo(before.start.x, before.start.y));
		path.getElements().add(new CubicCurveTo(before.handle1.x, before.handle1.y, before.handle2.x, before.handle2.y,
				before.end.x, before.end.y));
	}

	public Bezier split(double fraction)
	{
		// this is de casteljau's algorithm, see: https://stackoverflow.com/questions/2613788/algorithm-for-inserting-points-in-a-piecewise-cubic-b%C3%A9zier-path
		// P0_1 = (1-t)*P0 + t*P1
		// P1_2 = (1-t)*P1 + t*P2
		// P2_3 = (1-t)*P2 + t*P3
		Point P0_1 = proportionalInterpolation(fraction, start, handle1);
		Point P1_2 = proportionalInterpolation(fraction, handle1, handle2);
		Point P2_3 = proportionalInterpolation(fraction, handle2, end);

		// P01_12 = (1-t)*P0_1 + t*P1_2
		Point P01_12 = proportionalInterpolation(fraction, P0_1, P1_2);
		// P12_23 = (1-t)*P1_2 + t*P2_3
		Point P12_23 = proportionalInterpolation(fraction, P1_2, P2_3);

		// P0112_1223 = (1-t)*P01_12 + t*P12_23
		Point P0112_1223 = proportionalInterpolation(fraction, P01_12, P12_23);

		Bezier before = new Bezier(start, P0_1, P01_12, P0112_1223);
		return before;
	}

	private Point proportionalInterpolation(double fraction, Point first, Point second)
	{
		double oneMinusT = 1d - fraction;
		return new Point(oneMinusT * first.x + fraction * second.x, oneMinusT * first.y + fraction * second.y);
	}
}
