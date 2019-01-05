package org.geepawhill.contentment.fragments;

import javafx.scene.Group;
import org.geepawhill.contentment.core.Context;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExitTest
{
	private Context context;
	private Group group;

	@BeforeEach
	public void before()
	{
		context = new Context();
		group = new Group();
	}

	@Test
	public void removesGroup()
	{
		context.getCanvas().getChildren().add(group);
		Exit exit = new Exit( group );
		exit.prepare(context);
		exit.interpolate(context, 1);
		assertThat(context.getCanvas().getChildren().contains(group)).isFalse();
	}
	
	@Test
	public void isInstant()
	{
		context.getCanvas().getChildren().add(group);
		Exit exit = new Exit( group );
		exit.prepare(context);
		assertThat(exit.interpolate(context, .1)).isFalse();
	}
	
	@Test
	public void missingGroupThrows()
	{
		Exit exit = new Exit( group );
		exit.prepare(context);
		Assertions.assertThrows(NullPointerException.class, () -> {
			exit.interpolate(context, .1);
		});
	}
}
