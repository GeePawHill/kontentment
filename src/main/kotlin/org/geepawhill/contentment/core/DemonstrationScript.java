package org.geepawhill.contentment.core;

import static org.geepawhill.contentment.utility.JfxUtility.color;

import java.util.Vector;

import org.geepawhill.contentment.format.Format;
import org.geepawhill.contentment.geometry.*;
import org.geepawhill.contentment.grid.*;
import org.geepawhill.contentment.player.Script;
import org.geepawhill.contentment.position.*;
import org.geepawhill.contentment.rhythm.SimpleRhythm;
import org.geepawhill.contentment.step.ScriptBuilder;
import org.geepawhill.contentment.style.*;

import javafx.scene.paint.Paint;
import javafx.scene.text.*;

public class DemonstrationScript extends ScriptBuilder<DemonstrationScript> {
	private static final double XMARGIN = 20;
	private static final double YMARGIN = 20;

	private Format secondaryJumbo;
	private Format primaryJumbo;
	private Paint secondary;
	private Paint primary;
	private Paint emphatic;

	public DemonstrationScript() {
		super( new SimpleRhythm() );

		final double jumbo = 80d;
		final double normal = 55d;
		final double small = 45d;

		primary = color(119, 187, 65);
		secondary = color(177, 140, 254);
		emphatic = color(255, 255, 0);

		primaryJumbo = format(primary, jumbo);
		format(primary, normal);

		secondaryJumbo = format(secondary, jumbo);
		format(secondary, normal);

		format(emphatic, jumbo);
		format(emphatic, normal);
		new Format(format(emphatic, small),Frames.frame(emphatic, 3d, .7d));

	}

	private Format format(Paint majorColor, double fontsize) {
		Font font = Font.font("Chewed Pen BB", FontPosture.ITALIC, fontsize);
		return new Format(TypeFace.font(font, 2d, 1d), TypeFace.color(majorColor, 1d),
				Frames.frame(majorColor, 5d, 1d));
	}

	public Script make() {
		leadIn();
		noob();
		end();
		return script;
	}
	
	private void leadIn() {
		scene(0);
		wipe();
		letters("Demonstration Script").format(primaryJumbo).at(new TopLeft(XMARGIN, YMARGIN)).called("header").appear();
		assume(secondaryJumbo);
	}

	private void noob() {
		scene(5);
		wipe();
		header("A Box With Two Strokes In A Cross");
		Grid grid = new Grid(new PointPair(50,50,1550,850));
		Vertical middleThirdLeft = grid.vertical(33);
		Vertical middleThirdRight = grid.vertical(66);
		Horizontal middleThirdTop = grid.horizontal(33);
		Horizontal middleThirdBottom = grid.horizontal(66);
		
		PointPair middleThird = grid.area(middleThirdLeft, middleThirdTop, middleThirdRight, middleThirdBottom);
		assume(primaryJumbo);
		box(middleThird).appear();
		sync(1);
		stroke(middleThird).called("middle third").appear();
		stroke(new PointPair(middleThird.to.x,middleThird.from.y,middleThird.from.x,middleThird.to.y)).appear();
		
		cross("middle third", 100, 100, 0,0).appear();
	}

	public void header(String text) {
		letters(text).format(primaryJumbo).at(new TopLeft(XMARGIN, YMARGIN)).called("header").sketch();
	}
	
	private void headerEnd(String end) {
		letters(end).format(secondaryJumbo).at(new RightOf(actor("header").entrance())).sketch();
	}
	
	Vector<Point> polygon(int sides, double radius, Point at) {
		Vector<Point> result = new Vector<>();
		for (int i = 0; i < sides; i += 1) {
			double angle = ((double) i / (double) sides) * 2 * Math.PI;
			double pointX = (Math.sin(angle) * radius) + at.x;
			double pointY = (Math.cos(angle) * radius) + at.y;
			result.add(new Point(pointX, pointY));
		}
		return result;
	}

	public DemonstrationScript downcast() {
		return this;
	}
}
