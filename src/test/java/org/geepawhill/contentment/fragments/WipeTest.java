package org.geepawhill.contentment.fragments;
import static org.assertj.core.api.Assertions.assertThat;

import org.geepawhill.contentment.core.Context;
import org.junit.*;

import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WipeTest
{
	private Context context;

	@BeforeEach
	public void before()
	{
		context = new Context();
	}
	
	@Test
	public void wipes()
	{
		Wipe wipe = new Wipe();
		context.getCanvas().getChildren().add(new Text());
		wipe.prepare(context);
		assertThat(wipe.interpolate(context, 1d)).isFalse();
		assertThat(context.getCanvas().getChildren().size()).isEqualTo(0);
	}
}
