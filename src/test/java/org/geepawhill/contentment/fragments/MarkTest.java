package org.geepawhill.contentment.fragments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.geepawhill.contentment.test.ContentmentAssertions.assertThat;

import org.geepawhill.contentment.core.*;
import org.geepawhill.contentment.geometry.*;

import javafx.scene.Group;
import javafx.scene.shape.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MarkTest
{
	private Group group;
	private Bezier bezier;
	private Context context;

	@BeforeEach
	public void before()
	{
		context = new Context();
		group = new Group();
		bezier = new Bezier(new PointPair(0, 0, 100, 100));
	}

	@Test
	public void addsPath()
	{
		Mark mark = new Mark(group, BezierSource.Companion.value(bezier));
		mark.prepare(context);
		assertThat(group.getChildren().size()).isEqualTo(1);
	}
	
	@Test
	public void pathClearedAtZero()
	{
		Mark mark = new Mark(group, BezierSource.Companion.value(bezier));
		mark.prepare(context);
		assertThat(group.getChildren().size()).isEqualTo(1);
		Path path = (Path) group.getChildren().get(0);
		assertThat(path.getElements().size()).isEqualTo(0);
	}

	

	@Test
	public void addsTwoStepPath()
	{
		Mark mark = new Mark(group, BezierSource.Companion.value(bezier));
		mark.prepare(context);
		mark.interpolate(context, .25);
		assertThat(group.getChildren().size()).isEqualTo(1);
		Path path = (Path) group.getChildren().get(0);
		assertThat(path.getElements().size()).isEqualTo(2);
		assertThat(path.getElements().get(0)).isInstanceOf(MoveTo.class);
		assertThat(path.getElements().get(1)).isInstanceOf(CubicCurveTo.class);
	}

	@Test
	public void completedPathValues()
	{
		Mark mark = new Mark(group, BezierSource.Companion.value(bezier));
		mark.prepare(context);
		mark.interpolate(context, 1d);
		Path path = (Path) group.getChildren().get(0);
		MoveTo moveTo = (MoveTo) path.getElements().get(0);
		assertThat(new Point(moveTo.getX(), moveTo.getY())).isEqualTo(new Point(0, 0));
		CubicCurveTo curveTo = (CubicCurveTo) path.getElements().get(1);
		assertThat(new Point(curveTo.getX(), curveTo.getY())).isEqualTo(new Point(100, 100));
	}

	@Test
	public void partialPathValues()
	{
		Mark mark = new Mark(group, BezierSource.Companion.value(bezier));
		mark.prepare(context);
		mark.interpolate(context, .5d);
		Path path = (Path) group.getChildren().get(0);
		MoveTo moveTo = (MoveTo) path.getElements().get(0);
		assertThat(new Point(moveTo.getX(), moveTo.getY())).isEqualTo(new Point(0, 0));
		CubicCurveTo curveTo = (CubicCurveTo) path.getElements().get(1);
		assertThat(new Point(curveTo.getX(), curveTo.getY())).isEqualTo(new Point(50, 50));
	}
}
