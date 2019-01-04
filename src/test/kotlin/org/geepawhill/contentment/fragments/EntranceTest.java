package org.geepawhill.contentment.fragments;

import javafx.scene.Group;
import org.geepawhill.contentment.core.Context;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EntranceTest
{
	private Context context;

	@BeforeEach
	public void before()
	{
		context = new Context();
	}

	@Test
	public void addsNewGroup()
	{
		Entrance entrance = new Entrance(context.canvas, new Group() );
		entrance.prepare(context);
		entrance.interpolate(context, 1);
		assertThat(context.canvas.getChildren().contains(entrance.group())).isTrue();
	}
	
	@Test
	public void isInstant()
	{
		Entrance entrance = new Entrance(context.canvas, new Group());
		entrance.prepare(context);
		assertThat(entrance.interpolate(context, .1)).isFalse();
	}
}
