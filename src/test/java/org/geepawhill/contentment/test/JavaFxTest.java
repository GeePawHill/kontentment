package org.geepawhill.contentment.test;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import org.geepawhill.contentment.geometry.Point;
import org.geepawhill.contentment.geometry.PointPair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.geepawhill.contentment.test.ContentmentAssertions.assertThat;

public class JavaFxTest extends ContentmentTest
{

	@BeforeEach
	public void before()
	{
		
	}
	
	@Test
	public void whenIsBoundsActive()
	{
		Group group = new Group();
		Text text = new Text("Here's some text.");
		text.setTextAlignment(TextAlignment.LEFT);
		text.setTextOrigin(VPos.TOP);
		text.setTranslateX(100);
		text.setTranslateY(0);
		System.out.println(new PointPair(text));
		assertThat(new PointPair(text).getFrom()).isEqualTo(new Point(100,0));
		group.getChildren().add(text);
		
	}
}
