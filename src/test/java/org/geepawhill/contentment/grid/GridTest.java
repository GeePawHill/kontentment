package org.geepawhill.contentment.grid;

import static org.assertj.core.api.Assertions.assertThat;
import static org.geepawhill.contentment.test.ContentmentAssertions.assertThat;

import org.assertj.core.data.Offset;
import org.geepawhill.contentment.geometry.PointPair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GridTest
{
	private PointPair bounds;
	private Grid grid;

	@BeforeEach
	public void before()
	{
		bounds = new PointPair(0, 100, 300, 500);
		grid = new Grid(bounds);
	}

	@Test
	public void gridHasBorders()
	{
		assertThat(grid.top().points()).isEqualTo(bounds.northLine());
		assertThat(grid.left().points()).isEqualTo(bounds.westLine());
		assertThat(grid.bottom().points()).isEqualTo(bounds.southLine());
		assertThat(grid.right().points()).isEqualTo(bounds.eastLine());
	}

	@Test
	public void verticalAndHorizontal()
	{
		assertThat(grid.vertical(50).points()).isEqualTo(bounds.xCenterLine());
		assertThat(grid.vertical(25).points().getFrom().getX()).isCloseTo(bounds.along(.25).getX(), Offset.offset(1d));
		assertThat(grid.horizontal(50).points()).isEqualTo(bounds.yCenterLine());
		assertThat(grid.horizontal(25).points().getFrom().getY()).isCloseTo(bounds.along(.25).getY(), Offset.offset(1d));
	}

	@Test
	public void point()
	{
		assertThat(grid.point(grid.vertical(50), grid.horizontal(50))).isEqualTo(bounds.center());
		assertThat(grid.point(grid.vertical(33), grid.horizontal(33))).isEqualTo(bounds.along(.33));
	}

	@Test
	public void area()
	{
		assertThat( grid.area(grid.left(), grid.top(), grid.right(), grid.bottom())).isEqualTo(bounds);
		PointPair centerThird = new PointPair(bounds.along(.33), bounds.along(.66));
		assertThat( grid.area(grid.vertical(33), grid.horizontal(33), grid.vertical(66), grid.horizontal(66))).isEqualTo(centerThird);
	}
	
	@Test
	public void nested()
	{
		Grid square = new Grid(0,0,100,100);
		Horizontal newTop = square.horizontal(25);
		Horizontal newBottom = square.horizontal(50);
		Vertical newLeft = square.vertical(33);
		Vertical newRight = square.vertical(66);
		Grid nested = square.nested(newLeft,newTop,newRight,newBottom);
		assertThat(nested.all()).isEqualTo( new PointPair(33,25,66,50));
	}
}
